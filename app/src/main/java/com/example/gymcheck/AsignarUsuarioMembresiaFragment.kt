package com.example.gymcheck

import Controladores.PersonaControlador
import Controladores.UsuarioControlador
import Entidades.Persona
import Entidades.Usuario
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentAsignarUsuarioMembresiaBinding

class AsignarUsuarioMembresiaFragment : Fragment() {
    lateinit var binding:FragmentAsignarUsuarioMembresiaBinding
    var id = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(requireArguments().getString("uid")!=null){
            id = requireArguments().getString("uid").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAsignarUsuarioMembresiaBinding.inflate(layoutInflater)
        var nMembresia = " "

        val items = listOf("Semanal", "Quincenal", "Mensual")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.tfMembresia.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val autoCompleteTextView = binding.acMembresia
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position).toString()
            nMembresia = selected
        }

        binding.btnAgregar.setOnClickListener {
            val controlador = UsuarioControlador()
            val cedula = binding.etCedula.text.toString().lowercase().trim()

            if(cedula.isNullOrEmpty()){
                Toast.makeText(context, "Campo de cedula obligatoria", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val listaPersonas = controlador.mostrarUsuario()
            var usuario= Usuario(null, "negrachatel", "", 3, "", "", null)

            listaPersonas.forEach {
                if (it.cedula.equals(cedula)){
                    usuario = it
                }
            }

            controlador.editarUsuario(usuario.cedula, putIdMem(nMembresia))
            findNavController().navigate(R.id.action_asignarUsuarioMembresiaFragment_to_homeAdminFragment)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controlador = UsuarioControlador()
        val listaPersonas = controlador.mostrarUsuario()
        var usuario= Usuario(null, "negrachatel", "", 3, "", "", null)

        listaPersonas.forEach {
            if (it.idUsuario.toString() == id){
                usuario = it
            }
        }
        binding.etCedula.setText(id)

        binding.asignarMembresia.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_asignarUsuarioMembresiaFragment_to_buscarFragment)
        }
    }
    fun putIdMem(membresia:String):Int{
        var idMem = 0
        if(membresia.equals("Semanal")){
            idMem = 1
        } else if (membresia.equals("Quincenal")){
            idMem = 2
        } else if(membresia.equals("Mensual")){
            idMem = 3
        }
        return idMem
    }



    }

