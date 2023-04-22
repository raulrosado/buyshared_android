package com.example.buyshared.ui.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentLoginBinding
import com.example.buyshared.databinding.FragmentRegisterBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.ViewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val loginViewModel: LoginViewModel by viewModels()
    var logi = "buysharedLog"
    private var pDialog: ProgressDialog? = null

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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        inicio()
        return binding.root
    }

    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        binding.txtTengoCuenta.setOnClickListener {
            replaceFragment(R.id.contenedorFragment, LoginFragment(), fragmentTransaction)
        }

        binding.btnRegistro.setOnClickListener {
            if (binding.textName.text!!.isEmpty() || binding.textEmail.text!!.isEmpty() || binding.textPassword.text!!.isEmpty()) {
                Toast.makeText(requireContext(), "Ponga las credenciales", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginViewModel.registro(
                    binding.textName.text.toString(),
                    binding.textEmail.text.toString(),
                    binding.textPassword.text.toString(),
                    requireContext()
                )
            }
        }

        loginViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            Log.v(logi, "esta:" + it)
            if (it == true) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.loginRegister));
                    pDialog!!.show()
                }
            } else {
                if (pDialog!!.isShowing)
                    pDialog!!.dismiss()
            }
        })

        loginViewModel.isRegister.observe(viewLifecycleOwner, Observer {
            if (it) {
                replaceFragment(R.id.contenedorFragment, LoginFragment(), fragmentTransaction)
            }
            if (!it) {
                Toast.makeText(context,resources.getText(R.string.noRegistar),Toast.LENGTH_SHORT)
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
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
