package com.example.gymcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentAsignarUsuarioMembresiaBinding

class AsignarUsuarioMembresiaFragment : Fragment() {
    lateinit var binding:FragmentAsignarUsuarioMembresiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAsignarUsuarioMembresiaBinding.inflate(layoutInflater)


        val items = listOf("Semanal", "Quincenal", "Mensual")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.tfMembresia.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val autoCompleteTextView = binding.acMembresia
        autoCompleteTextView.setAdapter(adapter)


        binding.btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_asignarUsuarioMembresiaFragment_to_homeAdminFragment)
        }

        return binding.root
    }


}