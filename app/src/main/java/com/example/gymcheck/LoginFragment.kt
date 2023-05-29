package com.example.gymcheck

import Controladores.SesionControlador
import Controladores.UsuarioControlador
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.InetAddress


class LoginFragment : Fragment() {

    lateinit var binding:FragmentLoginBinding
    val userController = UsuarioControlador()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val session= context?.let { SesionControlador.getInstance(it) }
        if (session?.isLoggedIn() == true){
            findNavController().navigate(R.id.action_loginFragment_to_homeClientFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {

            var usuario = binding.tfUsuario.editText?.text.toString().trim()
            var contra = binding.tfPassword.editText?.text.toString().trim()
            validar(usuario, contra)
        }
    }

    fun validar(usuario:String, clave:String){
        if (usuario == "Admin" && clave == "Admin2023"){
            findNavController().navigate(R.id.action_loginFragment_to_homeAdminFragment)
        }else {
            val userAux = userController.validarUsuario(usuario, clave)
            if(userAux?.idUsuario != null){
                val sesionControlador = context?.let { SesionControlador.getInstance(it) }
                sesionControlador?.saveUserInfo(userAux.usuario, userAux.cedula)
                findNavController().navigate(R.id.action_loginFragment_to_homeClientFragment)
            }else{
                Toast.makeText(context, "No se ha podido iniciar sesion", Toast.LENGTH_SHORT).show()
            }
        }
    }

}