package Controladores

import Entidades.Anuncio
import Entidades.Producto
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import java.io.IOException

class AnuncioControlador {

    // IP Roberto: 192.168.0.15
    // IP Allan: 192.168.0.22
    // IP Marcelo: 192.168.1.11

    fun agregarAnuncio(anuncio: Anuncio, imgBytes: ByteArray?) {
        val urlAPI = "http://192.168.1.18/GymCheck-API/anuncio/agregar_anuncio.php"

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("tituloAnuncio", anuncio.tituloAnuncio)
        builder.addFormDataPart("descripcion", anuncio.descripcion)
        builder.addFormDataPart("fecha", anuncio.fecha)


        if (imgBytes != null) {
            builder.addFormDataPart("img", "product_image", RequestBody.create("image/jpeg".toMediaTypeOrNull(), imgBytes))
        }

        val requestBody = builder.build()

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


    fun editarAnucio(anuncio: Anuncio){
        val urlAPI = "http://192.168.0.15/GymCheck-API/producto/editar_producto.php"

        val formBody = FormBody.Builder()
            .add("idProducto", anuncio.idAnuncio.toString())
            .add("nombre", anuncio.tituloAnuncio)
            .add("descripcion", anuncio.descripcion)
            .add("precio", anuncio.fecha)

            .build()
        val request = Request.Builder()
            .url(urlAPI)
            .post(formBody)
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

    fun eliminarAnuncio(anuncio: Anuncio){


        val urlAPI = "http://192.168.1.18/GymCheck-API/anuncio/eliminar_anuncio.php"

        val formBody = FormBody.Builder()
            .add("idAnuncio", anuncio.idAnuncio.toString())
            .build()
        val request = Request.Builder()
            .url(urlAPI)
            .post(formBody)
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

    fun mostrarAnuncio(): List<Anuncio> = runBlocking {
        val anuncios = mutableListOf<Anuncio>()
        val urlAPI = "http://192.168.1.18/GymCheck-API/anuncio/mostrar_anuncio.php"

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

                    val idAnuncio = jsonObject.getInt("idAnuncio")
                    val tituloAnuncio = jsonObject.getString("tituloAnuncio")
                    val descripcion = jsonObject.getString("descripcion")
                    val fecha = jsonObject.getString("fecha")

                    val imagenBytes = Base64.decode(jsonObject.getString("img"), Base64.DEFAULT)

                    anuncios.add(Anuncio(idAnuncio,tituloAnuncio, descripcion, fecha, imagenBytes))
                }
            }
        }.join()

        anuncios
    }


}