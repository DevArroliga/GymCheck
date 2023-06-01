package com.example.gymcheck

import Adapters.ProductoAdapter
import Controladores.ProductoControlador
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentProductosClienteBinding

class ProductosClienteFragment : Fragment() {
    var controlador: ProductoControlador = ProductoControlador()
    lateinit var binding:FragmentProductosClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductosClienteBinding.inflate(layoutInflater)
        binding.bottomNavigation.selectedItemId = R.id.item_2

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_1 -> {
                    findNavController().navigate(R.id.action_productosClienteFragment_to_homeClientFragment)
                    true
                }

                R.id.item_2 -> {
                    Toast.makeText(context,"Productos", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item_3 -> {
                    findNavController().navigate(R.id.action_productosClienteFragment_to_anunciosClienteFragment)
                    true
                }

                else -> false
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productos = controlador.mostrarProducto()


        val productoAdapte = ProductoAdapter(productos)
        //productoAdapte.setOnEditItemClickListener(this)

        binding.rvProductos.adapter = productoAdapte
        binding.rvProductos.layoutManager = LinearLayoutManager(context)


    }
}