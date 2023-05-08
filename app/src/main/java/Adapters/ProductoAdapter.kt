package Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymcheck.R

class ProductoAdapter(activity: FragmentActivity?, controller: NavController):RecyclerView.Adapter<ProductoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  ProductoViewHolder(layoutInflater.inflate(R.layout.producto_layout, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {

    }

}