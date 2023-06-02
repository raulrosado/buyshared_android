package com.example.buyshared.ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.window.SplashScreen
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.buyshared.R
import com.example.buyshared.databinding.ActivityPrincipal2Binding
import com.example.buyshared.databinding.ActivityWelcomeBinding
import com.example.buyshared.ui.Fragment.LoginFragment

@dagger.hilt.android.AndroidEntryPoint
class Welcome : AppCompatActivity() {
    lateinit var tinyDB: TinyDB
    private lateinit var binding:ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)

        screenSplash.setKeepOnScreenCondition{false}

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configInicio()

    }

    private fun configInicio() {
        tinyDB = TinyDB(applicationContext)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (tinyDB.getString("server") == "") {
            tinyDB.putString("server", "http://192.168.1.252:4000/")
        }
        tinyDB.putInt("slide",1)

        Handler().postDelayed(Runnable {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }, 1000)
    }
}