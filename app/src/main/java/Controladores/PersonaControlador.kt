package Controladores
import Entidades.Persona
import Entidades.Usuario
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.random.Random

class PersonaControlador {
    val ipMarcelo2 = "192.168.0.4"
    // IP Roberto: 192.168.0.15
    // IP Allan: 192.168.0.22
    // IP Marcelo: 192.168.1.11
    // IP Marcelo 2: 192.168.0.7
    val ipAllan= "192.168.1.20"
    fun agregarPersona(persona: Persona){
        val controlador = UsuarioControlador()

        val urlAPI = "http://$ipMarcelo2/GymCheck-API/persona/agregar_persona.php"


        val requestBody: RequestBody = FormBody.Builder()
            .add("nombre", persona.nombre)
            .add("apellido", persona.apellido)
            .add("fechaNac", persona.fechaNac)
            .add("correo", persona.correo)
            .add("cedula", persona.cedula)
            .build()

        val request: Request = Request.Builder()
            .url(urlAPI)
            .post(requestBody)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{
            @SuppressLint("SimpleDateFormat")
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    val respuesta = response.body?.string()
                    println(respuesta)
                    val fechaHoy = Date()
                    val formato = SimpleDateFormat("yyyy-MM-dd")
                    val fechaFormateada = formato.format(fechaHoy)
                    Log.d("fecha", fechaFormateada.toString())
                    val usuario = Usuario(
                        null,
                        persona.nombre + Random.nextInt(1000,10000).toString(),
                        "Usuario123.",
                        1,
                        fechaFormateada.toString(),
                        persona.cedula,
                        null
                    )
                    controlador.agregarUsuario(usuario)
                    controlador.enviarEmailBienvenida(usuario.usuario, usuario.clave, persona.correo)
                }
                else {
                    println("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error en la petición HTTP: ${e.message}")
            }
        })
    }

    fun editarPersona(idPersona: Int, correo: String){
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/persona/editar_persona.php"


        val formBody = FormBody.Builder()
            .add("idPersona", idPersona.toString())
            .add("correo", correo)
            .build()
        val request = Request.Builder()
            .url(urlAPI)
            .post(formBody)
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    val respuesta = response.body?.string()
                    println(respuesta)
                }
                else {
                    println("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error en la petición HTTP: ${e.message}")
            }
        })
    }

    fun mostrarPersona(): List<Persona>{
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/persona/mostrar_persona.php"

        var listaAux = mutableListOf<Persona>()
        val request = Request.Builder()
            .url(urlAPI)
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val respuesta = response.body?.string()
                response.close()
                if (respuesta != null) {
                    val gson = Gson()
                    val listaPersonas = gson.fromJson(respuesta, Array<Persona>::class.java).toMutableList()
                    listaAux = listaPersonas
                } else {
                println("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error en la petición HTTP: ${e.message}")
            }
        })
        return listaAux
    }
}