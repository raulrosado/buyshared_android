package com.example.buyshared.ui.Fragment.Inside

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.buyshared.R
import com.example.buyshared.databinding.FragmentMainBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.Fragment.RegisterFragment
import com.example.buyshared.ui.ViewModel.LoginViewModel
import com.example.buyshared.ui.ViewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val mainViewModel: MainViewModel by viewModels()
    private var pDialog: ProgressDialog? = null
    var logi = "buysharedLog"
    lateinit var fragmentTransaction: FragmentTransaction
    var bottomSheetDialog: BottomSheetDialog? = null

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
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        inicio()
        return binding.root
    }

    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);

        bottomSheetDialog = BottomSheetDialog(requireContext())
        val viewrutas: View = layoutInflater.inflate(R.layout.addeventorlist, null)
        bottomSheetDialog!!.setContentView(viewrutas)
        bottomSheetDialog!!.setCanceledOnTouchOutside(true)

        binding.btnOptions.setOnClickListener {
            showMenu(it, R.menu.option)
        }

        binding.btnAddList.setOnClickListener{
            bottomSheetDialog!!.show()
        }

        mainViewModel.loadEventos(requireContext())
        mainViewModel.loadLists(requireContext())
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popupMenu = PopupMenu(context, v)
        // Inflating popup menu from popup_menu.xml file
        popupMenu.menuInflater.inflate(menuRes, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_setting -> {
                    // Respond to context menu item 1 click.
                    replaceFragment(R.id.contenedorFragmentPrincipal, SettingFragment(),fragmentTransaction)
                    true
                }
                R.id.menu_logout -> {
                    Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show()
                    true
                }
            }
            true
        }
        // Showing the popup menu
        popupMenu.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menu_setting -> {
                // Respond to context menu item 1 click.
                Toast.makeText(context, "setting", Toast.LENGTH_SHORT)
                true
            }
            R.id.menu_logout -> {
                // Respond to context menu item 2 click.
                Toast.makeText(context, "logout", Toast.LENGTH_SHORT)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}