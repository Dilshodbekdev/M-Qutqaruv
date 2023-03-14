package com.technocorp.mqutqaruv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.technocorp.mqutqaruv.databinding.ActivityMainBinding
import com.technocorp.mqutqaruv.util.SharedPref

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = SharedPref.getInstance(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        if (shared.register == true) {
            graph.setStartDestination(R.id.mapFragment)
        } else {
            graph.setStartDestination(R.id.registerFragment)
        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}