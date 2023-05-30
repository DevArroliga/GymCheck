package com.example.gymcheck

import Adapters.AnuncioAdapter
import Controladores.AnuncioControlador
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentAnunciosClienteBinding

class AnunciosClienteFragment : Fragment() {

    lateinit var binding:FragmentAnunciosClienteBinding
    var controlador: AnuncioControlador = AnuncioControlador()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAnunciosClienteBinding.inflate(layoutInflater)

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
        val anuncios = controlador.mostrarAnuncio()

        val anuncioAdapter = AnuncioAdapter(anuncios)
        //anuncioAdapter.setOnEditItemClickListener(this)

        binding.rvAnuncio.adapter = anuncioAdapter

        binding.rvAnuncio.layoutManager = LinearLayoutManager(context)
    }
}