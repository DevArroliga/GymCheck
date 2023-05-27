package com.example.gymcheck

import Controladores.ProductoControlador
import Entidades.Producto
import android.Manifest
import android.content.Context
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
import com.example.gymcheck.databinding.FragmentAgregarProductoBinding
import androidx.activity.result.PickVisualMediaRequest.*
import java.io.ByteArrayOutputStream


class AgregarProducto : Fragment() {
private var rutaimagen: String?= null

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            rutaimagen = uri.toString()
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val buffer = ByteArray(8192)
            val output = ByteArrayOutputStream()
            var bytesRead = inputStream!!.read(buffer)
            while (bytesRead != -1) {
                output.write(buffer, 0, bytesRead)
                bytesRead = inputStream.read(buffer)
            }
            val bytes = output.toByteArray()
            binding.imgProducto.setImageURI(uri)
        }

    }
    private lateinit var binding: FragmentAgregarProductoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgregarProductoBinding.inflate(layoutInflater)
        val controlador = ProductoControlador();

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.etNombreProducto.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val precioText = binding.etPrecio.text.toString()
            val stockText = binding.etStock.text.toString()

            if (nombre.isEmpty() || descripcion.isEmpty() || precioText.isEmpty() || stockText.isEmpty()) {
                Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val precio = precioText.toFloatOrNull()
            val stock = stockText.toIntOrNull()

            if (precio == null || stock == null || precio <= 0 || stock <= 0) {
                Toast.makeText(requireContext(), "Ingrese valores válidos para Precio y Stock", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoProducto = Producto(
                null,
                nombre,
                descripcion,
                precio,
                stock,
                null
            )
            val imgBytes = getImageBytes()
            controlador.agregarProducto(nuevoProducto, imgBytes)

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.imgProducto.setOnClickListener {
            inicioFoto()
        }
    }


    private fun init() {
        binding.prodcutoToolbar.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_agregarProducto_to_productosAdminFragment)
        }
    }

    private fun inicioFoto(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(
                    binding.imgProducto.context, Manifest.permission.READ_EXTERNAL_STORAGE
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
        val drawable = binding.imgProducto.drawable ?: return null
        val bitmap = (drawable as BitmapDrawable).bitmap
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }
}