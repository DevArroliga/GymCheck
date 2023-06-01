package Adapters

import Controladores.ProductoControlador
import Controladores.SesionControlador
import Entidades.Producto
import Entidades.Usuario
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.gymcheck.ProductosAdminFragment
import com.example.gymcheck.ProductosClienteFragment
import com.example.gymcheck.R
import com.example.gymcheck.databinding.ProductoLayoutBinding
import java.io.File

class ProductoAdapter(private var productos: List<Producto>, private var context: Context, private var controller:NavController) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    private val controlerProducto = ProductoControlador()
//    private var editItemClickListener: OnEditItemClickListener?= null

//    interface OnEditItemClickListener {
//        fun onEditItemClick(producto: Producto)
//
//    }
//
//   fun setOnEditItemClickListener(Listener: ProductosAdminFragment){
//       this.editItemClickListener = Listener
// }

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding =
            ProductoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    inner class ProductoViewHolder(private val binding: ProductoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentProducto: Producto? = null
        fun bind(producto: Producto) {

            currentProducto = producto
            binding.tvNombreSup.text = producto.nombre
            binding.tvDescriptionSup.text = producto.descripcion
            binding.tvStock.text = producto.stock.toString()
            binding.tvPrecio.text = producto.precio.toString()

            val imagen = convertirBytesAImagen(producto.imagen)

            if (imagen != null) {
                Glide.with(itemView.context)
                    .load(imagen)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgView)
            }

            val btnMenu = binding.btnMenu
            val session = SesionControlador.getInstance(context)
            if(session.isLoggedIn()){
                btnMenu.isInvisible = true
            }
            var prdto = producto.idProducto.toString()
            var bundle:Bundle? = Bundle()
            bundle!!.putString("pid", prdto)

            btnMenu.setOnClickListener {
                val popupMenu = PopupMenu(itemView.context, btnMenu)
                popupMenu.menuInflater.inflate(R.menu.menu_producto, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_editar -> {
                            controller.navigate(R.id.action_productosAdminFragment_to_actualizarProducto, bundle)
                            true
                        }
                        R.id.menu_eliminar -> {

                            currentProducto?.idProducto.let { id ->
                                controlerProducto.eliminarProducto(producto)
                                notifyItemRemoved(adapterPosition)
                                actualizarLista(controlerProducto.mostrarProducto())
                                notifyDataSetChanged()


                            }
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }


        }
    }

    fun convertirBytesAImagen(bytes: ByteArray?): Bitmap? {
        if (bytes == null) {
            return null
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


}