package com.example.buyshared.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentLoginBinding
import com.example.buyshared.ui.Activity.TinyDB
import com.karumi.dexter.Dexter
import android.Manifest.permission
import android.app.ProgressDialog
import android.content.Intent
import android.util.Log
import android.webkit.PermissionRequest
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.ViewModel.LoginViewModel
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val loginViewModel: LoginViewModel by viewModels()
    private var pDialog: ProgressDialog? = null
    var logi = "buysharedLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        inicio()
        return binding.root
    }

    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);

        requireActivity().getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Dexter.withActivity(requireActivity())
            .withPermissions(
                permission.CAMERA,
                permission.WRITE_EXTERNAL_STORAGE,
                permission.READ_EXTERNAL_STORAGE,
                permission.ACCESS_NETWORK_STATE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    if (!multiplePermissionsReport.areAllPermissionsGranted()) {
//                        Toast.makeText(requireContext(), "Permiso concedido", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    permissionToken: PermissionToken?
                ) {
                    Toast.makeText(requireContext(), "Permiso denegado", Toast.LENGTH_SHORT).show()
                    permissionToken?.continuePermissionRequest()
                }

            }).check()

        binding.txtNoCuenta.setOnClickListener {
            replaceFragment(R.id.contenedorFragment,RegisterFragment(),fragmentTransaction)
        }

        binding.btnLogin.setOnClickListener {
            if (binding.txtEmail.text!!.isEmpty() || binding.txtPassword.text!!.isEmpty()) {
                Toast.makeText(requireContext(), "Ponga las credenciales", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.login(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString(),
                    requireContext()
                )
            }
        }

        loginViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            Log.v(logi, "esta:" + it)
            if (it == true) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.loginLoading));
                    pDialog!!.show()
                }
            } else {
                if (pDialog!!.isShowing)
                    pDialog!!.dismiss()
            }
        })

        loginViewModel.isLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
//                startActivity(Intent(requireContext(),MapsActivity::class.java))
                Toast.makeText(context,"Login",Toast.LENGTH_SHORT)
            }
            if (!it) {
                Toast.makeText(context,"Nooooo Login",Toast.LENGTH_SHORT)
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}