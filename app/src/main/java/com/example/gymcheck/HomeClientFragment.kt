package com.example.gymcheck

import Controladores.SesionControlador
import Controladores.UsuarioControlador
import Entidades.Usuario
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentHomeClientBinding


class HomeClientFragment : Fragment() {

    lateinit var binding:FragmentHomeClientBinding
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeClientBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupToolbar()
        setUpDrawerNavigation()

        val sessionController = context?.let { SesionControlador.getInstance(it) }
        val usuario = sessionController?.getUsername()
        var textToShow = "BIENVENIDO A GYM-CHECK, $usuario"
        binding.tvSaludo.text = textToShow

        val controladorUser = UsuarioControlador()
        val lista = controladorUser.mostrarUsuario()
        var usuarioAux = Usuario(null, "", "", 1, null, "", null)
        lista.forEach {
            if(it.usuario == sessionController?.getUsername()){
                usuarioAux = it
            }
        }
        var diast = when(usuarioAux.idMembresia){
            1 -> 7
            2 -> 15
            3 -> 30
            else -> 1
        }
        val daysTr = controladorUser.calcularDiasTranscurridos(usuarioAux.fechaMembresia.toString())
        val daysR = if ((diast - daysTr) >= 0) diast - daysTr else 0

        binding.tvTiempo.text = "Te quedan $daysR dias"


    }

    private fun setUpDrawerNavigation() {
        val sessionController = context?.let { SesionControlador.getInstance(it) }

        binding.navegationView.setNavigationItemSelectedListener {menuItem->
            when(menuItem.itemId) {
                R.id.menu_logout ->{
                   sessionController?.clearSession()
                    findNavController().navigate(R.id.action_homeClientFragment_to_loginFragment)
                    true
                }
                else -> false
            }


        }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.topAppBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu)
        }

        binding.topAppBar.setNavigationOnClickListener {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                drawerLayout.openDrawer(GravityCompat.START)
            }

        }
    }


}