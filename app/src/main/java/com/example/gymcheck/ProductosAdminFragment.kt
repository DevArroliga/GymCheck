package com.example.gymcheck

import Adapters.ProductoAdapter
import Entidades.Producto
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentProductosAdminBinding
import com.example.gymcheck.databinding.ProductoLayoutBinding

class ProductosAdminFragment : Fragment() {

    lateinit var binding: FragmentProductosAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductosAdminBinding.inflate(layoutInflater)
        binding.bottomNavigation.selectedItemId = R.id.item_3

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_3 -> {
                    Toast.makeText(context,"Productos", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_2 -> {
                    findNavController().navigate(R.id.action_productosAdminFragment_to_membresiaAdminFragment)
                    true
                }
                R.id.item_1 -> {
                    findNavController().navigate(R.id.action_productosAdminFragment_to_homeAdminFragment)
                    true
                }

                R.id.item_4->{
                    findNavController().navigate(R.id.action_productosAdminFragment_to_anunciosAdminFragment)
                    true
                }

                else -> false
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productos = mutableListOf<Producto>()

        productos.add(Producto(1,"creatina", "Suplemento para aumentar masa",200f,20, " "))
        productos.add(Producto(1,"Perico", "Suplemento para quitar bajona",300f,15, " "))

        binding.rvProductos.adapter = ProductoAdapter(productos)
        binding.rvProductos.layoutManager = LinearLayoutManager(context)
    }

}