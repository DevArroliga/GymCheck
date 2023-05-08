package Adapters

import Entidades.Producto
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymcheck.R
import com.example.gymcheck.databinding.ProductoLayoutBinding

class ProductoAdapter(private val productos: List<Producto>):RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ProductoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        fun bind(producto: Producto) {
            binding.tvNombreSup.text = producto.nombre
            binding.tvDescriptionSup.text = producto.descripcion
            binding.tvStock.text = producto.stock.toString()
            binding.tvPrecio.text = producto.precio.toString()
        }
    }

}