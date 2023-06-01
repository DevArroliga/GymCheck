package com.example.gymcheck

import Adapters.AnuncioAdapter
import Controladores.AnuncioControlador
import Controladores.SesionControlador
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
import com.example.gymcheck.databinding.FragmentAnunciosClienteBinding

class AnunciosClienteFragment : Fragment() {

    lateinit var binding:FragmentAnunciosClienteBinding
    var controlador: AnuncioControlador = AnuncioControlador()

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAnunciosClienteBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        binding.bottomNavigation.selectedItemId = R.id.item_3

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_1 -> {
                    findNavController().navigate(R.id.action_anunciosClienteFragment_to_homeClientFragment)
                    true
                }

                R.id.item_2 -> {
                    findNavController().navigate(R.id.action_anunciosClienteFragment_to_productosClienteFragment)
                    true
                }

                R.id.item_3 -> {
                    Toast.makeText(context,"Anuncios", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupToolbar()
        setUpDrawerNavigation()

        val anuncios = controlador.mostrarAnuncio()
        var ex = findNavController()

        val anuncioAdapter = context?.let { AnuncioAdapter(anuncios, it,ex)  }
        //anuncioAdapter.setOnEditItemClickListener(this)

        binding.rvAnuncio.adapter = anuncioAdapter

        binding.rvAnuncio.layoutManager = LinearLayoutManager(context)
    }

    private fun setUpDrawerNavigation() {
        val sessionController = context?.let { SesionControlador.getInstance(it) }
        binding.navegationView.setNavigationItemSelectedListener {menuItem->
            when(menuItem.itemId) {
                R.id.menu_logout ->{
                    sessionController?.clearSession()
                    findNavController().navigate(R.id.action_anunciosClienteFragment_to_loginFragment)
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