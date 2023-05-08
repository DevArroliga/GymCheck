package Controladores
import Entidades.Persona
import okhttp3.*
import java.io.IOException

class PersonaControlador {
    private val urlAPI = "http://192.168.0.15/GymCheck-API/persona/agregar_persona.php"

    fun agregarPersona(persona: Persona){
        val requestBody: RequestBody = FormBody.Builder()
            .add("nombre", persona.nombre)
            .add("apellido", persona.apellido)
            .add("fechaNac", persona.fechaNac)
            .add("correo", persona.correo)
            .build()

        val request: Request = Request.Builder()
            .url(urlAPI)
            .post(requestBody)
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
}