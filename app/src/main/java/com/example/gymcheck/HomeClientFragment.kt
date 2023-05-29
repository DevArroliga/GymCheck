package com.example.gymcheck

import Controladores.SesionControlador
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentHomeClientBinding


class HomeClientFragment : Fragment() {

    lateinit var binding:FragmentHomeClientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeClientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sessionController = context?.let { SesionControlador.getInstance(it) }
        val usuario = sessionController?.getUsername()
        var textToShow = "Hola $usuario"
        binding.tvUsuario.text = textToShow
        binding.btnCerrarSesion.setOnClickListener {
            sessionController?.clearSession()
            findNavController().navigate(R.id.action_homeClientFragment_to_loginFragment)
        }

    }


}