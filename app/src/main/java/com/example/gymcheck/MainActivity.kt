package com.example.gymcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gymcheck.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navView: NavigationView

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {




        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav) as NavHostFragment
         navController = navHostFragment.navController
    }
}