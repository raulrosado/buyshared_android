package com.example.buyshared.ui.Fragment.Inside

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentSettingBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val settingViewModel: SettingViewModel by viewModels()
    private var pDialog: ProgressDialog? = null
    var logi = "buysharedLog"
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var uriImagen: Uri

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

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        inicio()
        return binding.root
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if(uri!= null){
            //imagen seleccionada
            Log.v(logi,"imagen seleccionada"+uri)
            uriImagen = uri
            binding.avatarPerfil.setImageURI(uri)
        }else{
            //no Image
            Log.v(logi,"imagen NO seleccionada")
        }
    }
    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);
        fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        var infoUser = JSONObject(tinyDB.getString("user").toString())
        binding.nameField.setText(infoUser.getString("name") + " " + infoUser.getString("apellidos"))
        binding.emailField.setText(infoUser.getString("email"))

        binding.btnBack.setOnClickListener {
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                MainFragment(),
                fragmentTransaction
            )
        }

        binding.btnUpdateInfo.setOnClickListener {
            settingViewModel.updateInfoPersonal(
                binding.emailField.text.toString(),
                binding.nameField.text.toString()
            )
        }

        binding.btnUpdatePassw.setOnClickListener {
            if (binding.oldPassword.text!!.isEmpty() || binding.newPass.text!!.isEmpty() || binding.newRepetPass.text!!.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.intoInfo), Toast.LENGTH_SHORT)
                    .show()
            } else {
                settingViewModel.updatePassword(
                    binding.oldPassword.text.toString(),
                    binding.newPass.text.toString(),
                    binding.newRepetPass.text.toString()
                )
            }
        }

        settingViewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.cargando));
                    pDialog!!.show()
                }
            } else {
                if (pDialog!!.isShowing) {
                    pDialog!!.hide()
                }
            }
        })

        binding.selectImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.btnChangeImage.setOnClickListener {
            settingViewModel.updateAvatar(uriImagen,requireActivity())
        }

        settingViewModel.isLoading.observe(viewLifecycleOwner, {
            if (it === true) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.cargando));
                    pDialog!!.show()
                }
            } else {
                if (pDialog!!.isShowing) {
                    pDialog!!.hide()
                }
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
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}