package com.example.buyshared.ui.Activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.buyshared.R
import com.example.buyshared.databinding.ActivityMainBinding
import com.example.buyshared.ui.Fragment.LoginFragment
import com.example.buyshared.ui.Fragment.WelcomeFragment

@dagger.hilt.android.AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val replaceFragment = ReplaceFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        screenSplash.setKeepOnScreenCondition{false}

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


