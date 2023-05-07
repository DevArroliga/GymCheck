package com.example.gymcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentHomeAdminBinding


class HomeAdminFragment : Fragment() {
    lateinit var binding:FragmentHomeAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeAdminBinding.inflate(layoutInflater)

        binding.bottomNavigation.selectedItemId = R.id.item_1

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_1 -> {

                    true
                }
                R.id.item_2 -> {
                    findNavController().navigate(R.id.action_homeAdminFragment_to_membresiaAdminFragment)
                    true
                }
                else -> false
            }

        }
    }

}