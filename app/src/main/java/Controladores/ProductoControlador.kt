package Controladores
import Entidades.Persona
import Entidades.Producto
import android.util.Base64
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import java.io.IOException

class ProductoControlador {
    val ipMarcelo2 = "192.168.0.7"
    // IP Roberto: 192.168.0.15
    // IP Allan: 192.168.0.22
    // IP Marcelo: 192.168.1.11
    val ipAllan= "192.168.1.20"
    fun agregarProducto(producto: Producto, imgBytes: ByteArray?) {
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/producto/agregar_producto.php"
        val urlAPI = "http://$ipAllan/GymCheck-API/producto/agregar_producto.php"

        if (producto.nombre.isNullOrEmpty() || producto.descripcion.isNullOrEmpty() || producto.precio <= 0 || producto.stock <= 0) {
            println("Ingrese valores válidos para todos los campos del producto")
            return
        }
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("nombre", producto.nombre)
        builder.addFormDataPart("descripcion", producto.descripcion)
        builder.addFormDataPart("precio", producto.precio.toString())
        builder.addFormDataPart("stock", producto.stock.toString())

        if (imgBytes != null) {
            builder.addFormDataPart("img", "product_image", RequestBody.create("image/jpeg".toMediaTypeOrNull(), imgBytes))
        }

        val requestBody = builder.build()

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


    fun editarProducto(producto: Producto, imgBytes: ByteArray?) {
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/producto/editar_producto.php"
        val urlAPI = "http://$ipAllan/GymCheck-API/producto/editar_producto.php"

        if (producto.nombre.isNullOrEmpty() || producto.descripcion.isNullOrEmpty() || producto.precio <= 0 || producto.stock <= 0) {
            println("Ingrese valores válidos para todos los campos del producto")
            return
        }

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("idProducto", producto.idProducto.toString())
        builder.addFormDataPart("nombre", producto.nombre)
        builder.addFormDataPart("descripcion", producto.descripcion)
        builder.addFormDataPart("precio", producto.precio.toString())
        builder.addFormDataPart("stock", producto.stock.toString())

        if (imgBytes != null) {
            builder.addFormDataPart("img", "product_image", RequestBody.create("image/jpeg".toMediaTypeOrNull(), imgBytes))
        }

        val requestBody = builder.build()

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

    fun eliminarProducto(producto: Producto){
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/producto/eliminar_producto.php"
        val urlAPI = "http://$ipAllan/GymCheck-API/producto/eliminar_producto.php"


        val formBody = FormBody.Builder()
            .add("idProducto", producto.idProducto.toString())
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

    fun mostrarProducto(): List<Producto> = runBlocking {
        val productos = mutableListOf<Producto>()
        val urlAPI = "http://$ipMarcelo2/GymCheck-API/producto/mostrar_producto.php"
        val urlAPI = "http://$ipAllan/GymCheck-API/producto/mostrar_producto.php"

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

                    val idProducto = jsonObject.getInt("idProducto")
                    val nombre = jsonObject.getString("nombre")
                    val descripcion = jsonObject.getString("descripcion")
                    val precio = jsonObject.getDouble("precio")
                    val stock = jsonObject.getInt("stock")
                    val imagenBytes = Base64.decode(jsonObject.getString("img"), Base64.DEFAULT)

                    productos.add(Producto(idProducto, nombre, descripcion, precio.toFloat(), stock, imagenBytes))
                }
            }
        }.join()

        productos
    }


}