package com.bb.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.price.adapter.CategoryListAdapter
import com.bb.price.model.CategoryItemsModel
import kotlinx.android.synthetic.main.category_items_row.view.*
import kotlinx.android.synthetic.main.fragment_category_items_list.*
import java.util.*


class CategoryItemsList : Fragment(), CategoryListAdapter.quantityChangeListener {


    var categoryAdapter: CategoryListAdapter? = null
    lateinit var categories: ArrayList<CategoryItemsModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cate_list_rcv.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryAdapter = CategoryListAdapter()
        cate_list_rcv.adapter = categoryAdapter
        categories = loadTheCategories()
        categoryAdapter!!.setdata(categories)
        categoryAdapter!!.setOnItemClickListener(this)
    }

    private fun loadTheCategories(): ArrayList<CategoryItemsModel> {

        val list = ArrayList<CategoryItemsModel>()

        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        list.add(
            CategoryItemsModel(
                resId = R.drawable.ic_launcher_background,
                title = "Tittle 1",
                act_price = 10.0,
                special_price = 2.0
            )
        )
        return list

    }


    override fun onPlusClick(position: Int, qty: Int?, view: View) {
        itemPlusAction(
            position,
            view.cate_item_qty.text.toString().toInt(),
            view
        )
    }

    override fun onMinusClick(position: Int, qty: Int?, view: View) {
        itemMinusAction(
            position,
            view.cate_item_qty.text.toString().toInt(),
            view
        )
    }


    private fun itemPlusAction(position: Int, currentQuantity: Int, view: View) {
        println("Action - plus called $currentQuantity and position is $position")


        var qty = currentQuantity
        if (qty >= 0) {
            Thread(Runnable {
                qty++
                // try to touch View of UI thread
                activity?.runOnUiThread {
                    view.cate_item_qty.text = qty.toString()
                }

            }).start()
        }
    }


    /**
     * decrease the quantity
     */

    private fun itemMinusAction(position: Int, currentQuantity: Int, view: View) {
        //  showLog("Minus called $currentQuantity and position is $position")
        println("Action - minus called $currentQuantity and position is $position")
        var qty = currentQuantity
        if (qty > 0) {
            Thread(Runnable {
                qty--
                // try to touch View of UI thread
                activity?.runOnUiThread {
                    view.cate_item_qty.text = qty.toString()

                }
            }).start()
        }

    }
}
