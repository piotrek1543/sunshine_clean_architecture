package com.piotrek1543.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.model.ForecastViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var forecastList: List<ForecastViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.dateTV.text = forecast.date
        holder.descriptionTV.text = forecast.description
        holder.tempMaxTV.text = forecast.tempMax
        holder.tempMinTV.text = forecast.tempMin

        Glide.with(holder.itemView.context)
                .load(R.mipmap.ic_launcher)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.weatherIconIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherIconIV : ImageView = view.findViewById(R.id.weather_icon)
        val dateTV: TextView = view.findViewById(R.id.date)
        val descriptionTV: TextView = view.findViewById(R.id.weather_description)
        val tempMaxTV: TextView = view.findViewById(R.id.high_temperature)
        val tempMinTV: TextView = view.findViewById(R.id.low_temperature)
    }

}