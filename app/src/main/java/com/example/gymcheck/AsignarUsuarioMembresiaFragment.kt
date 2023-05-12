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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            val cedula = binding.tfcedula.editText?.text.toString().lowercase().trim()

            val listaPersonas = controlador.mostrarUsuario()
            var usuario= Usuario(null, "negrachatel", "", 3, "", "", null)

            listaPersonas.forEach {
                if (it.cedula.equals(cedula)){
                    usuario = it
                }
            }


            controlador.editarUsuario(usuario.cedula, putIdMem(nMembresia))
            Toast.makeText(context, usuario.usuario, Toast.LENGTH_SHORT).show()

        }

        return binding.root
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