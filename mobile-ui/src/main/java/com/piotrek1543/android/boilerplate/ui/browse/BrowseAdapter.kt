package com.piotrek1543.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.model.ForecastViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var forecastList: List<ForecastViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.nameText.text = forecast.date
        holder.titleText.text = forecast.description

        /*Glide.with(holder.itemView.context)
                .load(bufferoo.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_bufferoo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.findViewById(R.id.text_name)
        var titleText: TextView = view.findViewById(R.id.text_title)
    }

}