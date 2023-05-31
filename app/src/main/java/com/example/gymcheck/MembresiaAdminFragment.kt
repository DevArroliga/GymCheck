package com.example.gymcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.gymcheck.databinding.FragmentMembresiaAdminBinding

class MembresiaAdminFragment : Fragment() {

    lateinit var binding:FragmentMembresiaAdminBinding
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMembresiaAdminBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        binding.bottomNavigation.selectedItemId = R.id.item_2

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupToolbar()
        setUpDrawerNavigation()

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_3 -> {
                    findNavController().navigate(R.id.action_membresiaAdminFragment_to_productosAdminFragment)
                    true
                }
                R.id.item_2 -> {
                    Toast.makeText(context,"Membresia", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_1 -> {
                    findNavController().navigate(R.id.action_membresiaAdminFragment_to_homeAdminFragment)
                    true
                }
                R.id.item_4->{
                    findNavController().navigate(R.id.action_membresiaAdminFragment_to_anunciosAdminFragment)
                    true
                }
                else -> false
            }

        }



        val items = listOf("Semanal", "Quincenal", "Mensual")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.tfMembresia.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val autoCompleteTextView = binding.acMembresia
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position).toString()
            binding.tfNombre.editText?.setText(selected)
            binding.tfDuracion.editText?.setText(putDuration(selected).toString())
        }
    }

    private fun setUpDrawerNavigation() {
        binding.navegationView.setNavigationItemSelectedListener {menuItem->
            when(menuItem.itemId) {
                R.id.menu_logout ->{
                    findNavController().navigate(R.id.action_membresiaAdminFragment_to_loginFragment)
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

    fun putDuration(duration:String):Int{
        var days = 0

        if(duration.equals("Semanal")){
            days = 7
        }else if (duration.equals("Quincenal")){
            days = 15
        }else if (duration.equals("Mensual")){
            days = 30
        }

        return days
    }

}