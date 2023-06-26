package com.example.buyshared.ui.Fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentWelcomeBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()

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
        _binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        tinyDB = TinyDB(requireContext())
        requireActivity().getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (tinyDB.getString("server") == "") {
//            tinyDB.putString("server", "http://192.168.1.252:4000/")
            tinyDB.putString("server", "https://buyshared.azurewebsites.net/")
        }
        tinyDB.putInt("slide",1)

        Handler().postDelayed(Runnable {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            replaceFragment(
                R.id.contenedorFragment,
                LoginFragment(),
                fragmentTransaction
            )
        }, 1000)

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WelcomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}