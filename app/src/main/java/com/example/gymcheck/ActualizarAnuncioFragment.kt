package com.example.gymcheck

import Controladores.AnuncioControlador
import Entidades.Anuncio
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide.init
import com.example.gymcheck.databinding.FragmentActualizarAnuncioBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class ActualizarAnuncioFragment : Fragment() {
private lateinit var anuncio: Anuncio

    private lateinit var binding: FragmentActualizarAnuncioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActualizarAnuncioBinding.inflate(layoutInflater)


        val controlador = AnuncioControlador()
        binding.btnActualizar.setOnClickListener {
            val actualizarAnuncio = Anuncio(
                binding.etIdAnuncio.text.toString().toInt(),
                binding.etNombreAnuncio.text.toString(),
                binding.etDescripcion.text.toString(),
                binding.etFechaVence.text.toString(),
                null

            )
              controlador.editarAnucio(actualizarAnuncio)

            }
        return binding.root
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        showDatePicker()







    }

    private fun init() {
        binding.actualizarToolbar.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarAnuncioFragment_to_anunciosAdminFragment)
        }
    }
    private fun showDatePicker(){

        binding.etFechaVence.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha vencimiento")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            picker.show(childFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                val selectedDate = Date(it)
                val asf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                binding.tfFechaVence.editText?.setText(asf.format(selectedDate))
            }
        }

    }


}