package Adapters

import Entidades.Anuncio
import Entidades.Producto
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.gymcheck.databinding.AnuncioLayoutBinding
import com.example.gymcheck.databinding.ProductoLayoutBinding


class AnuncioAdapter(private val anuncio: List<Anuncio>): RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        val binding = AnuncioLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnuncioViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return anuncio.size
    }

    override fun onBindViewHolder(holder: AnuncioAdapter.AnuncioViewHolder, position: Int) {
        holder.bind(anuncio[position])
    }

    inner class AnuncioViewHolder(private val binding: AnuncioLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anuncio: Anuncio) {
            binding.tvNombreAnun.text = anuncio.tituloAnuncio
            binding.tvDescriptionAnun.text = anuncio.descripcion
            binding.tvFecha.text = anuncio.fecha


            val imagen = convertirBytesAImagen(anuncio.img)

            if (imagen != null) {
                Glide.with(itemView.context)
                    .load(imagen)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgView)
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