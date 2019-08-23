package com.bb.price.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bb.price.R
import com.bb.price.model.LaunchScreenModel
import kotlinx.android.synthetic.main.launching_screen_row_grid.view.*

/**
Created by $USER_NAME on 23-Aug-19.
 */
class LaunchScreenAdapter(val categoriesList: ArrayList<LaunchScreenModel>) :
    RecyclerView.Adapter<LaunchScreenAdapter.ViewHolder>() {
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
                R.layout.launching_screen_row_grid,
                parent,
                false
            )
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(launchScreenModel: LaunchScreenModel) {

            itemView.launch_screen_row_grid_title.text = launchScreenModel.title

            launchScreenModel.resId.let {

                itemView.launch_screen_row_grid_image.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        launchScreenModel.resId!!
                    )
                )
            }


            itemView.launch_screen_row_grid.setOnClickListener {

                Toast.makeText(itemView.context, itemView.launch_screen_row_grid_title.text, Toast.LENGTH_SHORT).show()
            }
        }

    }
}