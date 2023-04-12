package com.example.buyshared.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buyshared.R
import com.example.buyshared.databinding.ActivityMainBinding
import com.example.buyshared.ui.Fragment.LoginFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val replaceFragment = ReplaceFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configInicio()
    }

    private fun configInicio() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        replaceFragment(R.id.contenedorFragment, LoginFragment(), fragmentTransaction)
    }
}

annotation class AndroidEntryPoint


