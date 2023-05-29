package com.example.gymcheck

import Adapters.ProductoAdapter
import Controladores.ProductoControlador
import Entidades.Anuncio
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

class ProductosAdminFragment : Fragment(), ProductoAdapter.OnEditItemClickListener {

    var controlador: ProductoControlador = ProductoControlador()
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

        binding.btnRefrescar.setOnClickListener {
            findNavController().navigate(R.id.productosAdminFragment)
        }
        binding.btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_productosAdminFragment_to_agregarProducto)
        }


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




        val productos = controlador.mostrarProducto()


        val productoAdapte = ProductoAdapter(productos)
        productoAdapte.setOnEditItemClickListener(this)

        binding.rvProductos.adapter = productoAdapte
        binding.rvProductos.layoutManager = LinearLayoutManager(context)
    }



    override fun onEditItemClick(producto: Producto) {
        findNavController().navigate(R.id.action_productosAdminFragment_to_actualizarProducto)
    }


}