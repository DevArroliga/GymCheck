package com.example.gymcheck

import Controladores.AnuncioControlador
import Entidades.Anuncio
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentAgregarAnuncioBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AgregarAnuncio : Fragment() {


    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri->
        if(uri != null){
            binding.imgAnuncio.setImageURI(uri)
        }

    }
private lateinit var binding: FragmentAgregarAnuncioBinding
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
        binding = FragmentAgregarAnuncioBinding.inflate(layoutInflater)

        val controlador = AnuncioControlador()
        binding.btnAgregar.setOnClickListener {
            val nuevoAnuncio = Anuncio(
                null,
                binding.etNombreAnuncio.text.toString(),
                binding.etDescripcion.text.toString(),
                binding.etFechaVence.text.toString(),
                null
            )
            val imgeBytes = getImageBytes()
            controlador.agregarAnuncio(nuevoAnuncio, imgeBytes)
        }

        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        showDatePicker()

        binding.imgAnuncio.setOnClickListener {
            inicioFoto()
        }
    }


    private fun init(){
      binding.anuncioToolbar.btnBack.setOnClickListener {
          findNavController().navigate(R.id.action_agregarAnuncio_to_anunciosAdminFragment)
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
    private fun inicioFoto(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(
                    binding.imgAnuncio.context, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED->{
                    lanzarFoto()
                }
                else -> requestPermissionLaucher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }else{
            lanzarFoto()

        }


    }
    private val requestPermissionLaucher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            lanzarFoto()
        } else {
            Toast.makeText(context, "Activar los permisos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun lanzarFoto() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun getImageBytes(): ByteArray? {
        val drawable = binding.imgAnuncio.drawable ?: return null
        val bitmap = (drawable as BitmapDrawable).bitmap
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }


}