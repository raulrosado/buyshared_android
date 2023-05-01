package com.example.buyshared.ui.Fragment.Inside

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyshared.R
import com.example.buyshared.adapter.EventAdapter
import com.example.buyshared.adapter.ListsAdapter
import com.example.buyshared.databinding.FragmentMainBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
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

    lateinit var _binding: FragmentMainBinding
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
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)

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

        bottomSheetDialog!!.findViewById<Button>(R.id.btnCancel)!!.setOnClickListener {
            bottomSheetDialog!!.hide()
        }
        bottomSheetDialog!!.findViewById<Button>(R.id.btnAdd)!!.setOnClickListener {
            mainViewModel.insertListRetrofit(
                bottomSheetDialog!!.findViewById<TextInputEditText>(R.id.txtName)!!.text.toString(),
                "1",
                "",
                ""
            )
            if (!pDialog!!.isShowing) {
                pDialog!!.setMessage(resources.getString(R.string.insertMessage));
                pDialog!!.show()
            }
        }


        val recyclerViewEvent = binding.recyclerEventos
        val recyclerViewList = binding.recyclerLista
        recyclerViewEvent.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerViewList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        mainViewModel.loadEventos(requireContext())
        mainViewModel.loadLists(requireContext())

        mainViewModel.isLoading.observe(viewLifecycleOwner,{
            if (it === true) {
                binding.progressBarLogin.visibility = View.VISIBLE
            }else{
                binding.progressBarLogin.visibility = View.GONE
            }
        })

        mainViewModel.isInsert.observe(viewLifecycleOwner,{
            Log.v(logi,"Insertando......"+it)
            if (it === true) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.insertMessage));
                    pDialog!!.show()
                }
            }else{
                if (pDialog!!.isShowing) {
                    pDialog!!.hide()
                }
                bottomSheetDialog!!.hide()
            }
        })

        mainViewModel.eventView.observe(viewLifecycleOwner,{
            binding.layoutEvents.visibility = it
        })

        mainViewModel.listEvents.observe(viewLifecycleOwner,{
            recyclerViewEvent.adapter = EventAdapter(it)
            Log.v("buysharedLog","cantidad eventos:"+it.size)
            if(it.size === 0){
                binding.layoutEvents.visibility = View.GONE
            }else{
                binding.layoutEvents.visibility = View.VISIBLE
            }
        })

        mainViewModel.listLists.observe(viewLifecycleOwner,{
            recyclerViewList.adapter = ListsAdapter(it)
        })




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