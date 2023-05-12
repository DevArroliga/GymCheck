package Controladores

import Entidades.Persona
import Entidades.Producto
import Entidades.Usuario
import android.util.Base64
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class UsuarioControlador {
    fun agregarUsuario(usuario:Usuario){

        // IP Roberto: 192.168.0.15
        // IP Allan: 192.168.0.22
        // IP Marcelo: 192.168.1.11

        val urlAPI = "http://192.168.0.7/GymCheck-API/usuario/agregar_usuario.php"

        val requestBody: RequestBody = FormBody.Builder()
            .add("usuario", usuario.usuario)
            .add("clave", usuario.clave)
            .add("cedula", usuario.cedula)
            .add("fechaMembresia", usuario.fechaMembresia.toString())
            .build()

        val request: Request = Request.Builder()
            .url(urlAPI)
            .post(requestBody)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
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
    fun editarUsuario(cedula: String, idMembresia:Int){
        val urlAPI = "http://192.168.0.7/GymCheck-API/usuario/editar_usuario.php"

        val formBody = FormBody.Builder()
            .add("cedula", cedula)
            .add("idMembresia", idMembresia.toString())
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
    fun mostrarUsuario(): List<Usuario> = runBlocking {
        val usuarios = mutableListOf<Usuario>()
        val urlAPI = "http://192.168.0.7/GymCheck-API/usuario/mostrar_usuario.php"

        launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url(urlAPI)
                .build()

            val client = OkHttpClient()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }

                val json = response.body!!.string()
                val jsonArray = JSONArray(json)

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

                    val idUsuario = jsonObject.getInt("idUsuario")
                    val usuario = jsonObject.getString("usuario")
                    val clave = jsonObject.getString("clave")
                    val activo = jsonObject.getInt("activo")
                    val fechaMembresia = jsonObject.getString("fechaMembresia")
                    val cedula = jsonObject.getString("cedula")
                    val idMembresia = jsonObject.get("idMembresia") as? Int ?: null

                    usuarios.add(Usuario(idUsuario, usuario, clave, activo, fechaMembresia, cedula, idMembresia))
                }
            }
        }.join()

        usuarios
    }
}