package com.example.buyshared.ui.Fragment.Inside

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentEventDetailBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EventDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding:FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val mainViewModel: MainViewModel by viewModels()
    private var pDialog: ProgressDialog? = null
    var logi = "buysharedLog"
    lateinit var fragmentTransaction: FragmentTransaction

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
        _binding = FragmentEventDetailBinding.inflate(inflater,container,false)
        inicio()
        return binding.root
    }

    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);
        fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        val idEvent = tinyDB.getString("eventSel")
        val infoEvent = mainViewModel.getInfoEventById(idEvent.toString())

        binding.btnBackEvent.setOnClickListener {
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                MainFragment(),
                fragmentTransaction
            )
        }

        binding.titleEvent.text = infoEvent!!.nombre
        Glide.with(requireContext()).load("https://buyshare.onrender.com/images/"+infoEvent.bg).into(binding.imgBGEvent);
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}