package com.example.gymcheck

import Controladores.SesionControlador
import Controladores.UsuarioControlador
import Entidades.Usuario
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentUsuarioInfoBinding

class UsuarioInfoFragment : Fragment() {

    lateinit var binding:FragmentUsuarioInfoBinding
    var clicked = false
    val session = context?.let { SesionControlador.getInstance(it) }
    val uController = UsuarioControlador()
    val lista = uController.mostrarUsuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsuarioInfoBinding.inflate(layoutInflater)

        binding.tvNombreUsuario.text = session?.getUsername()
        binding.tvCedula.text = session?.getCedula()
        verFormNuevoPss()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.tvTipoMembresia.text = membresia()

        binding.btnCambiarContra.setOnClickListener {
            clicked = true
            verFormNuevoPss()
        }
        binding.btnGuardarNPss.setOnClickListener {
            val passAct = binding.etContraActual.text.toString()
            val passN = binding.etContraNueva.text.toString()
            val passNRpt = binding.etContraNuevaRpt.text.toString()

            validarContra(passAct, passN, passNRpt)
        }
        binding.btnCancelarNPss.setOnClickListener {
            clicked = false
            verFormNuevoPss()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_usuarioInfoFragment_to_homeClientFragment)
        }
    }
    fun verFormNuevoPss(){
        binding.layoutClave.isVisible = clicked
    }
    fun validarContra(passAct:String, passN:String, passNRpt:String){

        var usuario = Usuario(null, "", "", 1, null, "", null)

        lista.forEach {
            if (it.cedula == session?.getCedula()){
                usuario = it
            }
        }
        if (usuario.clave == passAct){
            if (passN == passNRpt){
                session?.getCedula()?.let { uController.cambiarClave(it, passN) }
                clicked = false
                verFormNuevoPss()
            }else{
                Toast.makeText(context, "Contraseña nueva no coinciden", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
    fun membresia():String{
        var membresia = ""
        var usuario = Usuario(null, "", "", 1, null, "", null)
        lista.forEach {
            if (it.cedula == session?.getCedula()){
                usuario = it
            }
        }
        when(usuario.idMembresia){
            1 -> membresia = "Semanal"
            2 -> membresia = "Quincenal"
            3 -> membresia = "Mensual"

            else -> membresia = "Error"
        }
        return membresia
    }
}