package com.example.gymcheck

import Adapters.ProductoAdapter
import Controladores.ProductoControlador
import Entidades.Producto
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentProductosAdminBinding

class ProductosAdminFragment : Fragment(), ProductoAdapter.OnEditItemClickListener {

    var controlador: ProductoControlador = ProductoControlador()
    lateinit var binding: FragmentProductosAdminBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentProductosAdminBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        binding.bottomNavigation.selectedItemId = R.id.item_3


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

        setupToolbar()
        setUpDrawerNavigation()




        val productos = controlador.mostrarProducto()


        val productoAdapte = context?.let { ProductoAdapter(productos, it) }

        if (productoAdapte != null) {
            productoAdapte.setOnEditItemClickListener(this)
        }

        binding.rvProductos.adapter = productoAdapte
        binding.rvProductos.layoutManager = LinearLayoutManager(context)
    }

    private fun setUpDrawerNavigation() {
        binding.navegationView.setNavigationItemSelectedListener {menuItem->
            when(menuItem.itemId) {
                R.id.menu_logout ->{
                    findNavController().navigate(R.id.action_productosAdminFragment_to_loginFragment)
                    true
                }
                else -> false

            }


        }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.topAppBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu)
        }

        binding.topAppBar.setNavigationOnClickListener {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                drawerLayout.openDrawer(GravityCompat.START)
            }

        }
    }


    override fun onEditItemClick(producto: Producto) {
        findNavController().navigate(R.id.action_productosAdminFragment_to_actualizarProducto)
    }


}