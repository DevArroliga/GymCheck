package com.example.gymcheck

import Controladores.PersonaControlador
import Entidades.Persona
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.CustomToolbarBinding
import com.example.gymcheck.databinding.FragmentClienteNuevoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class ClienteNuevo : Fragment() {

    private lateinit var binding: FragmentClienteNuevoBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClienteNuevoBinding.inflate(layoutInflater)
        val controlador = PersonaControlador()

        binding.btnAgregar.setOnClickListener {

            val nuevaPersona = Persona(
                binding.etNombre.text.toString(),
                binding.etApellido.text.toString(),
                binding.etFechaNac.text.toString(),
                binding.etEmail.text.toString()
            )
            controlador.agregarPersona(nuevaPersona)

            back()
        }


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        showDatePicker()

    }

    private fun init(){
        binding.customToolbar.btnBack.setOnClickListener {
            back()
        }
    }

    fun back(){
        findNavController().navigate(R.id.action_clienteNuevo_to_homeAdminFragment)
    }
    private fun showDatePicker(){

        binding.tfFechaNac.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            picker.show(childFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                val selectedDate = Date(it)
                val asf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                binding.tfFechaNac.editText?.setText(asf.format(selectedDate))
            }
        }

    }

}