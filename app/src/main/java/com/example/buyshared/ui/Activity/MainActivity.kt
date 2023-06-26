package com.example.buyshared.ui.Activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.buyshared.R
import com.example.buyshared.databinding.ActivityMainBinding
import com.example.buyshared.ui.Fragment.LoginFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener


@dagger.hilt.android.AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val replaceFragment = ReplaceFragment()
    lateinit var tinyDB: TinyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        screenSplash.setKeepOnScreenCondition{false}

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configInicio()
    }

    private fun configInicio() {
        tinyDB = TinyDB(applicationContext)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        replaceFragment(R.id.contenedorFragment, LoginFragment(), fragmentTransaction)

        if (tinyDB.getString("server") == "") {
            tinyDB.putString("server", "https://buyshared.azurewebsites.net/")
//            tinyDB.putString("server", "http://192.168.1.252:4000/")
        }
        tinyDB.putInt("slide",1)

        var permiso = tinyDB.getBoolean("permisoBack")
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    tinyDB.putBoolean("permisoBack", true)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }
}

annotation class AndroidEntryPoint


