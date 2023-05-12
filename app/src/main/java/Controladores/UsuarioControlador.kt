package Controladores

import Entidades.Usuario
import okhttp3.*
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
}