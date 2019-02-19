package com.piotrek1543.android.boilerplate.ui.forecast

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.model.ForecastViewModel
import com.piotrek1543.android.boilerplate.ui.utils.SunshineWeatherUtils
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ForecastAdapter @Inject constructor(
        val context: Context,
        private val weatherUtils: SunshineWeatherUtils) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var forecastList: List<ForecastViewModel> = arrayListOf()
    private val mUseTodayLayout: Boolean = context.resources.getBoolean(R.bool.use_today_layout)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecastList[position]
        with(holder) {

            if (position == 0)
                dateTV.text = SimpleDateFormat("EEEE", Locale.ENGLISH).format(forecast.date * 1000L)
            else
                dateTV.text = DateUtils.formatDateTime(context, forecast.date * 1000L, DateUtils.FORMAT_SHOW_DATE);

            //val timestamp = forecast.date * 1000L
            // dateTV.text = SunshineDateUtils.getFriendlyDateString(context, timestamp, false)
            descriptionTV.text = weatherUtils.getStringForWeatherCondition(context, forecast.icon)
            tempMaxTV.text = weatherUtils.formatTemperature(context, forecast.tempMax)
            tempMinTV.text = weatherUtils.formatTemperature(context, forecast.tempMin)

            val weatherImageId = when (itemViewType) {
                VIEW_TYPE_TODAY -> weatherUtils
                        .getLargeArtResourceIdForWeatherCondition(forecast.icon)
                VIEW_TYPE_FUTURE_DAY -> weatherUtils
                        .getSmallArtResourceIdForWeatherCondition(forecast.icon)
                else -> R.drawable.ic_cloudy
            }

            weatherIconIV.setImageResource(weatherImageId)

            itemView.setOnClickListener {
                Toast.makeText(it.context!!, forecast.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutId = when (viewType) {
            VIEW_TYPE_TODAY -> R.layout.list_item_forecast_today
            VIEW_TYPE_FUTURE_DAY -> R.layout.forecast_list_item
            else -> throw IllegalArgumentException("Invalid view type, value of $viewType")
        }

        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)
                .apply { isFocusable = true }

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mUseTodayLayout && position == 0) {
            VIEW_TYPE_TODAY
        } else {
            VIEW_TYPE_FUTURE_DAY
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val weatherIconIV: ImageView = view.findViewById(R.id.weather_icon)
        val dateTV: TextView = view.findViewById(R.id.date)
        val descriptionTV: TextView = view.findViewById(R.id.weather_description)
        val tempMaxTV: TextView = view.findViewById(R.id.high_temperature)
        val tempMinTV: TextView = view.findViewById(R.id.low_temperature)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val forecast = forecastList[adapterPosition]
            Toast.makeText(v.context!!, forecast.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val VIEW_TYPE_TODAY = 0
        private const val VIEW_TYPE_FUTURE_DAY = 1
    }

}