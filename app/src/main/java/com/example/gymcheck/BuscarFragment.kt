package com.example.gymcheck

import Adapters.UsuarioAdapter
import Controladores.UsuarioControlador
import Entidades.Usuario
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymcheck.databinding.FragmentBuscarBinding

class BuscarFragment : Fragment(), UsuarioAdapter.OnEditItemClickListener {

    lateinit var binding:FragmentBuscarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuscarBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lista = UsuarioControlador().mostrarUsuario()
        val listaAux = mutableListOf<Usuario>()

        var ex = findNavController()
        val usuarioAdapter = UsuarioAdapter(lista, ex)
        usuarioAdapter.setOnEditClickListener(this)

        binding.rvUsuarios.adapter = usuarioAdapter
        binding.rvUsuarios.layoutManager = LinearLayoutManager(context)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_buscarFragment_to_homeAdminFragment)
        }
        binding.tfBuscar.setEndIconOnClickListener {
            if(listaAux.isNotEmpty()){
                listaAux.clear()
            }
            lista.forEach {
                if (it.cedula == binding.tfBuscar.editText?.text.toString()){
                    listaAux.add(it)
                }
            }

            if(binding.tfBuscar.editText?.text.isNullOrEmpty()){
                usuarioAdapter.actualizarLista(lista)
                usuarioAdapter.notifyDataSetChanged()
            }else{
                usuarioAdapter.actualizarLista(listaAux)
                usuarioAdapter.notifyDataSetChanged()
            }

        }

    }

    override fun onEditItemClick(usuario: Usuario) {
        findNavController().navigate(R.id.action_buscarFragment_to_asignarUsuarioMembresiaFragment)
    }


}