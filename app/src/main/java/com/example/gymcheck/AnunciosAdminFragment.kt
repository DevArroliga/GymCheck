package com.example.gymcheck

import Adapters.AnuncioAdapter
import Adapters.ProductoAdapter
import Controladores.AnuncioControlador
import Entidades.Anuncio
import Entidades.Producto
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentAnunciosAdminBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnunciosAdminFragment : Fragment(), AnuncioAdapter.OnEditItemClickListener {
    var controlador: AnuncioControlador = AnuncioControlador()

    private lateinit var binding: FragmentAnunciosAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnunciosAdminBinding.inflate(layoutInflater)

        binding.bottomNavigation.selectedItemId = R.id.item_4
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRefrescar.setOnClickListener {
            findNavController().navigate(R.id.anunciosAdminFragment)
        }


        binding.btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_anunciosAdminFragment_to_agregarAnuncio)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_4 -> {
                    Toast.makeText(context, "Anuncios", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item_3 -> {
                    findNavController().navigate(R.id.action_anunciosAdminFragment_to_productosAdminFragment)
                    true
                }

                R.id.item_2 -> {
                    findNavController().navigate(R.id.action_anunciosAdminFragment_to_membresiaAdminFragment)
                    true
                }
                R.id.item_1 -> {
                    findNavController().navigate(R.id.action_anunciosAdminFragment_to_homeAdminFragment)
                    true
                }

                else -> false

            }

        }


        val anuncios = controlador.mostrarAnuncio()

        val anuncioAdapter = AnuncioAdapter(anuncios)
        anuncioAdapter.setOnEditItemClickListener(this)

        binding.rvAnuncio.adapter = anuncioAdapter

        binding.rvAnuncio.layoutManager = LinearLayoutManager(context)




    }

    override fun onEditItemClick(anuncio: Anuncio) {
        findNavController().navigate(R.id.action_anunciosAdminFragment_to_actualizarAnuncioFragment)
    }


}