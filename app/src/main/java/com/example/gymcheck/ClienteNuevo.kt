package com.example.gymcheck

import Adapters.UsuarioAdapter
import Controladores.PersonaControlador
import Entidades.Persona
import Entidades.Usuario
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.CustomToolbarBinding
import com.example.gymcheck.databinding.FragmentClienteNuevoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class ClienteNuevo : Fragment(), UsuarioAdapter.OnEditItemClickListener {

    private lateinit var binding: FragmentClienteNuevoBinding



    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fechaHoy = Date()
        val formato = SimpleDateFormat("yyyy/MM/dd")
        val fechaFormateada = formato.format(fechaHoy)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClienteNuevoBinding.inflate(layoutInflater)
        val controlador = PersonaControlador()

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val fechaNac = binding.etFechaNac.text.toString()
           val  email = binding.etEmail.text.toString()
            val cedula = binding.etCedula.text.toString().lowercase().trim()

            if (nombre.isNullOrEmpty()||apellido.isNullOrEmpty()||fechaNac.isNullOrEmpty()||email.isNullOrEmpty()||cedula.isNullOrEmpty()){
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }else{

                val nuevaPersona = Persona(
                    null,
                    nombre,
                    apellido,
                    fechaNac,
                    email,
                    cedula
                )
                controlador.agregarPersona(nuevaPersona)

                next()
            }

        }

        binding.btnAtras.setOnClickListener {
            back()
        }

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init()

        showDatePicker()

    }

    /*private fun init(){
        binding.customToolbar.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_clienteNuevo_to_homeAdminFragment)
        }
    }*/

    fun next(){
        var cedula = binding.etCedula.text
        var bundle:Bundle? = Bundle()
        bundle!!.putString("uid", cedula.toString())
        findNavController().navigate(R.id.action_clienteNuevo_to_asignarUsuarioMembresiaFragment, bundle)
    }
    fun back(){
        findNavController().navigate(R.id.action_clienteNuevo_to_homeAdminFragment)
    }
    private fun showDatePicker() {
        binding.tfFechaNac.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            picker.show(childFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                val selectedDate = Instant.ofEpochMilli(it)
                    .atZone(ZoneId.of("UTC"))
                    .toLocalDate()
                val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault())
                binding.tfFechaNac.editText?.setText(formatter.format(selectedDate))
            }
        }
    }

    override fun onEditItemClick(usuario: Usuario){
        findNavController().navigate(R.id.action_buscarFragment_to_asignarUsuarioMembresiaFragment)

    }

}