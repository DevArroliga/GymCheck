package com.example.gymcheck

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentHomeAdminBinding


class HomeAdminFragment : Fragment() {
    lateinit var binding:FragmentHomeAdminBinding
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
        binding = FragmentHomeAdminBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        binding.bottomNavigation.selectedItemId = R.id.item_1

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setUpDrawerNavigation()

        binding.agregarPersona.setOnClickListener {
            findNavController().navigate(R.id.action_homeAdminFragment_to_clienteNuevo)
        }

        binding.buscarUsuario.setOnClickListener{
            findNavController().navigate(R.id.action_homeAdminFragment_to_buscarFragment)
        }

        binding.gestionarProductos.setOnClickListener{
            findNavController().navigate(R.id.action_homeAdminFragment_to_productosAdminFragment)
        }

        binding.gestionarAnuncios.setOnClickListener{
            findNavController().navigate(R.id.action_homeAdminFragment_to_anunciosAdminFragment)
        }

        binding.gestionarMembresias.setOnClickListener{
            findNavController().navigate(R.id.action_homeAdminFragment_to_membresiaAdminFragment)
        }

        /*binding.perfilUsuario.setOnClickListener {
            findNavController().navigate(R.id.action_homeAdminFragment_to_membresiaAdminFragment)
        }*/

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_1 -> {
                 Toast.makeText(context,"Home", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item_2 -> {
                    findNavController().navigate(R.id.action_homeAdminFragment_to_membresiaAdminFragment)
                    true
                }

                R.id.item_3 -> {
                    findNavController().navigate(R.id.action_homeAdminFragment_to_productosAdminFragment)
                    true
                }

                R.id.item_4 ->{
                    findNavController().navigate(R.id.action_homeAdminFragment_to_anunciosAdminFragment)
                    true
                }
                else -> false
            }

        }
    }

    private fun setUpDrawerNavigation() {
binding.navegationView.setNavigationItemSelectedListener {menuItem->
    when(menuItem.itemId) {
R.id.menu_logout ->{
    findNavController().navigate(R.id.action_homeAdminFragment_to_loginFragment)
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


}