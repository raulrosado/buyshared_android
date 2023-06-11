package com.example.buyshared.ui.Fragment.Inside

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyshared.R
import com.example.buyshared.adapter.AvatarAdapter
import com.example.buyshared.adapter.TasksAdapter
import com.example.buyshared.databinding.FragmentDetailBinding
import com.example.buyshared.ui.Activity.ReplaceFragment
import com.example.buyshared.ui.Activity.TinyDB
import com.example.buyshared.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()
    private val mainViewModel: MainViewModel by viewModels()
    private var pDialog: ProgressDialog? = null
    var logi = "buysharedLog"
    lateinit var fragmentTransaction: FragmentTransaction
    var position = 0
    var bottomSheetDialog: BottomSheetDialog? = null
    var bottomSheetDialogAddSolicitud: BottomSheetDialog? = null

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
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        inicio()
        return binding.root
    }

    private fun inicio() {
        tinyDB = TinyDB(requireContext())
        pDialog = ProgressDialog(requireContext());
        pDialog!!.setCancelable(true);
        fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        val idList = tinyDB.getString("listSel")
        val infoList = mainViewModel.getInfoListById(idList.toString())

        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialogAddSolicitud = BottomSheetDialog(requireContext())
        val viewrutas: View = layoutInflater.inflate(R.layout.addtask, null)
        val viewrutasSolicitud: View = layoutInflater.inflate(R.layout.addsolicitud, null)
        bottomSheetDialog!!.setContentView(viewrutas)
        bottomSheetDialogAddSolicitud!!.setContentView(viewrutasSolicitud)
        bottomSheetDialog!!.setCanceledOnTouchOutside(true)
        bottomSheetDialogAddSolicitud!!.setCanceledOnTouchOutside(true)

        binding.includeNavBar.btnBackEvent.setOnClickListener {
            replaceFragment(
                R.id.contenedorFragmentPrincipal,
                MainFragment(),
                fragmentTransaction
            )
        }

        binding.titleListInfo.text = infoList!!.nombre
        mainViewModel.loadTaskList(infoList._id)

        mainViewModel.isLoadTask.observe(viewLifecycleOwner,{
            if(it){
                binding.loadListDetail.visibility = View.VISIBLE
            }else{
                binding.loadListDetail.visibility = View.GONE
            }
        })

        val recyclerTaskListDetail = binding.recyclerTaskListDetail
        recyclerTaskListDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val recyclerAvatarsListDetail = binding.recyclerAvatarList
        recyclerAvatarsListDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mainViewModel.listTasks.observe(viewLifecycleOwner,{
            recyclerTaskListDetail.adapter = TasksAdapter(
                it,
                requireActivity(),
                mainViewModel,
                "list"
            )
        })

        mainViewModel.listAvatarsList.observe(viewLifecycleOwner,{
            recyclerAvatarsListDetail.adapter = AvatarAdapter(it,requireActivity())
        })

        mainViewModel.positionEdit.observe(viewLifecycleOwner,{
            if(it !== null) {
                Log.v(logi, "position:" + mainViewModel.positionEdit.value)
                recyclerTaskListDetail.adapter = TasksAdapter(mainViewModel.listTasks.value!!,requireActivity(),mainViewModel,"list")
            }
        })

        binding.includeNavBar.btnAddListTask.setOnClickListener {
            bottomSheetDialog!!.show()
            bottomSheetDialog!!.findViewById<TextInputEditText>(R.id.txtName)!!.setText("")
        }

        bottomSheetDialog!!.findViewById<Button>(R.id.btnCancel)!!.setOnClickListener {
            bottomSheetDialog!!.hide()
        }
        bottomSheetDialogAddSolicitud!!.findViewById<Button>(R.id.btnCancel)!!.setOnClickListener {
            bottomSheetDialogAddSolicitud!!.hide()
        }

        bottomSheetDialog!!.findViewById<Button>(R.id.btnAddTask)!!.setOnClickListener {
            mainViewModel.addTask("list",
                "",
                tinyDB.getString("listSel").toString(),
                tinyDB.getString("listSel").toString(),
                bottomSheetDialog!!.findViewById<TextInputEditText>(R.id.txtName)!!.text.toString()
            )
            bottomSheetDialog!!.hide()
        }

        bottomSheetDialogAddSolicitud!!.findViewById<Button>(R.id.btnAddSolicitud)!!.setOnClickListener {
            mainViewModel.addSolicitud(
                bottomSheetDialogAddSolicitud!!.findViewById<TextInputEditText>(R.id.txtEmailSolicitud)!!.text.toString(),
                tinyDB.getString("eventSel").toString(),
                tinyDB.getString("listSel").toString(),requireContext()
            )
            bottomSheetDialogAddSolicitud!!.hide()
        }



        binding.includeNavBar.btnOptions.setOnClickListener {
            showMenu(it, R.menu.optiondetail)
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner,{
            if(it){
                if (!pDialog!!.isShowing) {
                    pDialog!!.setMessage(resources.getString(R.string.cargando));
                    pDialog!!.show()
                }
            }else{
                if (pDialog!!.isShowing) {
                    pDialog!!.hide()
                }
            }
        })

        mainViewModel.isDel.observe(viewLifecycleOwner,{
            if(it){
                replaceFragment(
                    R.id.contenedorFragmentPrincipal,
                    MainFragment(),
                    fragmentTransaction
                )
            }
        })
    }

    private fun showMenu(it: View?, @MenuRes menuRes: Int) {
        val popupMenu = PopupMenu(context, it)
        // Inflating popup menu from popup_menu.xml file
        popupMenu.menuInflater.inflate(menuRes, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_addFriend -> {
                    // Respond to context menu item 1 click.
                    bottomSheetDialogAddSolicitud!!.show()
                    true
                }

                R.id.menu_delList -> {
                    mainViewModel.delList(
                        tinyDB.getString("listSel").toString()
                    )
                    true
                }
            }
            true
        }
        // Showing the popup menu
        popupMenu.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}