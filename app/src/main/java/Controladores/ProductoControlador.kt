package Controladores
import Entidades.Persona
import Entidades.Producto
import android.util.Base64
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class ProductoControlador {
    // IP Roberto: 192.168.0.15
    // IP Allan: 192.168.0.22
    // IP Marcelo: 192.168.1.11

    fun agregarProducto(producto: Producto){
        val urlAPI = "http://192.168.1.21/GymCheck-API/producto/agregar_producto.php"
        val imgBase64 = Base64.encodeToString(producto.img, Base64.DEFAULT)

        val requestBody: RequestBody = FormBody.Builder()
            .add("nombre", producto.nombre)
            .add("descripcion", producto.descripcion)
            .add("precio", producto.precio.toString())
            .add("stock", producto.stock.toString())
            .add("img" ,imgBase64 )

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

    fun editarProducto(producto: Producto){
        val urlAPI = "http://192.168.0.15/GymCheck-API/producto/editar_producto.php"

        val formBody = FormBody.Builder()
            .add("idProducto", producto.idProducto.toString())
            .add("nombre", producto.nombre)
            .add("descripcion", producto.descripcion)
            .add("precio", producto.precio.toString())
            .add("stock", producto.stock.toString())
            .add("img", producto.stock.toString())
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

    fun eliminarProducto(producto: Producto){
        val urlAPI = "http://192.168.0.15/GymCheck-API/producto/eliminar_producto.php"

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

    fun mostrarProducto(listaProducto: List<Producto>){
        val urlAPI = "http://192.168.0.15/GymCheck-API/producto/mostrar_producto.php"

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
                    val listaProductos = gson.fromJson(respuesta, Array<Producto>::class.java).toList()
                } else {
                    println("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error en la petición HTTP: ${e.message}")
            }
        })
    }
}