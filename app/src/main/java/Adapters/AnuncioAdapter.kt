package Adapters

import Entidades.Anuncio
import Entidades.Producto
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        }
    }

}