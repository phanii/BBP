package com.bb.price

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bb.price.adapter.LaunchScreenAdapter
import com.bb.price.model.LaunchScreenModel
import kotlinx.android.synthetic.main.fragment_launching_screen.*
import java.util.*

class LaunchingScreenFragment : Fragment() {
    lateinit var settingsList: ArrayList<String>
    var filterdNames = ArrayList<LaunchScreenModel>()
    lateinit var categories: ArrayList<LaunchScreenModel>
    var launchScreenAdapter: LaunchScreenAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launching_screen, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsList = arrayListOf()
        launchScreenAdapter = LaunchScreenAdapter()

        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        categories = loadTheCategories()




        launchscreen_items_category_rcv.apply {

            hasFixedSize()
            layoutManager = GridLayoutManager(context, 3)
            /**
             * add divider lines
             */
            //addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = launchScreenAdapter
        }
        launchScreenAdapter?.setdata(categories)
        launchscreen_search_field.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }

    private fun loadTheCategories(): ArrayList<LaunchScreenModel> {

        val list = ArrayList<LaunchScreenModel>()

        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Cerials"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Dry Fruits"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Flours"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Oils"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "FMCG"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Plastic Items"))
        list.add(LaunchScreenModel(resId = R.drawable.ic_launcher_background, title = "Fruits"))
        return list

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.cart_fragment) {
            findNavController().navigate(R.id.cart_fragment)
            return true

        }

        return super.onOptionsItemSelected(item)
    }


    private fun filter(text: String) {
        filterdNames.let {
            it.clear()
        }
        //new array list that will hold the filtered data
        filterdNames = ArrayList<LaunchScreenModel>()

        //looping through existing elements
        for (s in categories) {
            //if the existing elements contains the search input
            if (s.title?.toLowerCase()?.contains(text.toLowerCase())!!) {
                //adding the element to filtered list
                filterdNames.add(s)
            }
        }
        launchScreenAdapter?.filterList(filterdNames)
    }

}
