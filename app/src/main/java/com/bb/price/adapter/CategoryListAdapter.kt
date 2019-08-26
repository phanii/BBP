package com.bb.price.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bb.price.R
import com.bb.price.model.CategoryItemsModel
import kotlinx.android.synthetic.main.category_items_row.view.*


/**
Created by $USER_NAME on 23-Aug-19.
 */
class CategoryListAdapter :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private var categoriesList = emptyList<CategoryItemsModel>()

    companion object {

        private var sClickListener: quantityChangeListener? = null
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launchScreenModel = categoriesList[position]
        holder.bindView(launchScreenModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_items_row,
                parent,
                false
            )
        )
    }

    internal fun setOnItemClickListener(clickListener: quantityChangeListener) {
        sClickListener = clickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            launchScreenModel: CategoryItemsModel
        ) {

            itemView.cate_item_title.text = launchScreenModel.title
            itemView.cate_item_act_price.text = "${launchScreenModel.act_price.toString()}"
            itemView.cate_item_spec_price.text = "${launchScreenModel.special_price.toString()}"

            launchScreenModel.resId.let {

                itemView.cate_item_image.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        launchScreenModel.resId!!
                    )
                )
            }

            itemView.cate_item_plus.setOnClickListener {

                sClickListener?.onPlusClick(
                    adapterPosition,
                    itemView.cate_item_qty.text.toString().toInt(),
                    itemView
                )
            }
            itemView.cate_item_minus.setOnClickListener {

                sClickListener?.onMinusClick(
                    adapterPosition,
                    itemView.cate_item_qty.text.toString().toInt(),
                    itemView
                )
            }
        }

    }

    fun setdata(categoriesList: List<CategoryItemsModel>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    internal interface quantityChangeListener {
        fun onPlusClick(position: Int, qty: Int? = 0, view: View)
        fun onMinusClick(position: Int, qty: Int? = 0, view: View)
    }
}