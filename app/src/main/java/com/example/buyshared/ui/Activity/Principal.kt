package com.example.buyshared.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buyshared.R
import com.example.buyshared.databinding.ActivityMainBinding
import com.example.buyshared.databinding.ActivityPrincipal2Binding
import com.example.buyshared.ui.Fragment.Inside.MainFragment
import com.example.buyshared.ui.Fragment.WelcomeFragment

@dagger.hilt.android.AndroidEntryPoint
class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipal2Binding
    val replaceFragment = ReplaceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipal2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        configInicio()
    }

    private fun configInicio() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        replaceFragment(R.id.contenedorFragmentPrincipal, MainFragment(), fragmentTransaction)
    }
}