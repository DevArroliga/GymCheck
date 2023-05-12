package Adapters

import Entidades.Producto
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.gymcheck.R
import com.example.gymcheck.databinding.ProductoLayoutBinding
import java.io.File

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


            Glide.with(binding.root.context)
                .load(producto.imagen)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgView)



        }
    }

}