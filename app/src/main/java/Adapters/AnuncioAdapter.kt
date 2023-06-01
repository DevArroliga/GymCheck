package Adapters

import Controladores.AnuncioControlador
import Controladores.SesionControlador
import Entidades.Anuncio
import Entidades.Producto
import Entidades.Usuario
import android.annotation.SuppressLint
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
import com.example.gymcheck.R
import com.example.gymcheck.databinding.AnuncioLayoutBinding
import com.example.gymcheck.databinding.ProductoLayoutBinding

class AnuncioAdapter(private var anuncio: List<Anuncio>, private var context: Context, private var controller: NavController) :
    RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder>() {

//    private var editItemClickListener: OnEditItemClickListener? = null

  interface OnEditItemClickListener{
      fun onEditItemClick(anuncio: Anuncio)
  }

//fun setOnEditItemClickListener(Listener: OnEditItemClickListener?){
//this.editItemClickListener = Listener
//}




    private val anuncioControlador = AnuncioControlador()

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
    fun actualizarLista(nuevaLista: List<Anuncio>) {
        anuncio = nuevaLista
    }

    inner class AnuncioViewHolder(private val binding: AnuncioLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private var currentAnuncio: Anuncio? = null

        @SuppressLint("NotifyDataSetChanged")
        fun bind(anuncio: Anuncio) {
            currentAnuncio = anuncio

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

            val btnMenu = binding.btnMenu

            val session = SesionControlador.getInstance(context)
            if (session.isLoggedIn()){
                btnMenu.isInvisible = true  
            }
            var an = anuncio.idAnuncio
            var bundle:Bundle? = Bundle()
            bundle!!.putString("aid", an.toString())
            btnMenu.setOnClickListener {
                val popupMenu = PopupMenu(itemView.context, btnMenu)
                popupMenu.menuInflater.inflate(R.menu.menu_anuncio, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_editar -> {
//                            currentAnuncio?.let { anuncio ->
//                                editItemClickListener?.onEditItemClick(anuncio)
//                            }

                            controller.navigate(R.id.action_anunciosAdminFragment_to_actualizarAnuncioFragment, bundle)
                            true
                        }


                        R.id.menu_eliminar -> {
                            currentAnuncio?.idAnuncio?.let { id ->
                                anuncioControlador.eliminarAnuncio(anuncio)
                                // Notifica al RecyclerView que un elemento ha sido eliminado.
                                actualizarLista(AnuncioControlador().mostrarAnuncio())
                                notifyItemRemoved(adapterPosition)
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

        fun convertirBytesAImagen(bytes: ByteArray?): Bitmap? {
            if (bytes == null) {
                return null
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }



    }
}
