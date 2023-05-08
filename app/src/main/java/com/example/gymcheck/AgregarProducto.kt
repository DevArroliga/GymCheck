package com.example.gymcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentAgregarProductoBinding

class AgregarProducto : Fragment() {

private lateinit var binding: FragmentAgregarProductoBinding
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
        binding = FragmentAgregarProductoBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        binding.prodcutoToolbar.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_agregarProducto_to_productosAdminFragment)
        }
    }

}