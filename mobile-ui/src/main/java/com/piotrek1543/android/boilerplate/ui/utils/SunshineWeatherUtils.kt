package com.piotrek1543.android.boilerplate.ui.utils

import android.content.Context
import com.piotrek1543.android.boilerplate.cache.PreferencesHelper
import com.piotrek1543.android.boilerplate.ui.R
import timber.log.Timber
import javax.inject.Inject


/**
 * Contains useful utilities for a weather app, such as conversion between Celsius and Fahrenheit,
 * from kph to mph, and from degrees to NSEW.  It also contains the mapping of weather condition
 * codes in OpenWeatherMap to strings.  These strings are contained
 */
class SunshineWeatherUtils @Inject constructor(private val prefs: PreferencesHelper) {

    /**
     * This method will convert a temperature from Celsius to Fahrenheit.
     *
     * @param temperatureInCelsius Temperature in degrees Celsius(°C)
     * @return Temperature in degrees Fahrenheit (°F)
     */
    private fun celsiusToFahrenheit(temperatureInCelsius: Double): Double {
        return temperatureInCelsius * 1.8 + 32
    }

    /**
     * Temperature data is stored in Celsius by our app. Depending on the user's preference,
     * the app may need to display the temperature in Fahrenheit. This method will perform that
     * temperature conversion if necessary. It will also format the temperature so that no
     * decimal points show. Temperatures will be formatted to the following form: "21°"
     *
     * @param context     Android Context to access preferences and resources
     * @param temperature Temperature in degrees Celsius (°C)
     * @return Formatted temperature String in the following form:
     * "21°"
     */
    fun formatTemperature(context: Context, temperature: Double): String {
        val temp = if (!prefs.isMetric) celsiusToFahrenheit(temperature) else temperature

        val temperatureFormatResourceId = R.string.format_temperature

        /* For presentation, assume the user doesn't care about tenths of a degree. */
        return String.format(context.getString(temperatureFormatResourceId), temp)
    }

    /**
     * This method will format the temperatures to be displayed in the
     * following form: "HIGH° / LOW°"
     *
     * @param context Android Context to access preferences and resources
     * @param high    High temperature for a day in user's preferred units
     * @param low     Low temperature for a day in user's preferred units
     * @return String in the form: "HIGH° / LOW°"
     */
    fun formatHighLows(context: Context, high: Double, low: Double): String {
        val roundedHigh = Math.round(high)
        val roundedLow = Math.round(low)

        val formattedHigh = formatTemperature(context, roundedHigh.toDouble())
        val formattedLow = formatTemperature(context, roundedLow.toDouble())

        return "$formattedHigh / $formattedLow"
    }

    /**
     * This method uses the wind direction in degrees to determine compass direction as a
     * String. (eg NW) The method will return the wind String in the following form: "2 km/h SW"
     *
     * @param context   Android Context to access preferences and resources
     * @param windSpeed Wind speed in kilometers / hour
     * @param degrees   Degrees as measured on a compass, NOT temperature degrees!
     * See https://www.mathsisfun.com/geometry/degrees.html
     * @return Wind String in the following form: "2 km/h SW"
     */
    fun getFormattedWind(context: Context, windSpeed: Float, degrees: Float): String {
        var windSpeed = windSpeed
        var windFormat = R.string.format_wind_kmh

        if (!prefs.isMetric) {
            windFormat = R.string.format_wind_mph
            windSpeed *= .621371192237334f
        }

        /*
         * You know what's fun? Writing really long if/else statements with tons of possible
         * conditions. Seriously, try it!
         */
        var direction = "Unknown"
        when {
            degrees >= 337.5 || degrees < 22.5 -> direction = "N"
            degrees >= 22.5 && degrees < 67.5 -> direction = "NE"
            degrees >= 67.5 && degrees < 112.5 -> direction = "E"
            degrees >= 112.5 && degrees < 157.5 -> direction = "SE"
            degrees >= 157.5 && degrees < 202.5 -> direction = "S"
            degrees >= 202.5 && degrees < 247.5 -> direction = "SW"
            degrees >= 247.5 && degrees < 292.5 -> direction = "W"
            degrees >= 292.5 && degrees < 337.5 -> direction = "NW"
        }

        return String.format(context.getString(windFormat), windSpeed, direction)
    }

    /**
     * Helper method to provide the stringId according to the weather
     * condition id returned by the OpenWeatherMap call.
     *
     * @param context   Android context
     * @param weatherId from OpenWeatherMap API response
     * See http://openweathermap.org/weather-conditions for a list of all IDs
     * @return Int for the weather condition
     */
    fun getStringIdForWeatherCondition(weatherId: Int): Int = when (weatherId) {
        in 200..232 -> R.string.condition_2xx
        in 300..321 -> R.string.condition_3xx
        else -> when (weatherId) {
            500 -> R.string.condition_500
            501 -> R.string.condition_501
            502 -> R.string.condition_502
            503 -> R.string.condition_503
            504 -> R.string.condition_504
            511 -> R.string.condition_511
            520 -> R.string.condition_520
            531 -> R.string.condition_531
            600 -> R.string.condition_600
            601 -> R.string.condition_601
            602 -> R.string.condition_602
            611 -> R.string.condition_611
            612 -> R.string.condition_612
            615 -> R.string.condition_615
            616 -> R.string.condition_616
            620 -> R.string.condition_620
            621 -> R.string.condition_621
            622 -> R.string.condition_622
            701 -> R.string.condition_701
            711 -> R.string.condition_711
            721 -> R.string.condition_721
            731 -> R.string.condition_731
            741 -> R.string.condition_741
            751 -> R.string.condition_751
            761 -> R.string.condition_761
            762 -> R.string.condition_762
            771 -> R.string.condition_771
            781 -> R.string.condition_781
            800 -> R.string.condition_800
            801 -> R.string.condition_801
            802 -> R.string.condition_802
            803 -> R.string.condition_803
            804 -> R.string.condition_804
            900 -> R.string.condition_900
            901 -> R.string.condition_901
            902 -> R.string.condition_902
            903 -> R.string.condition_903
            904 -> R.string.condition_904
            905 -> R.string.condition_905
            906 -> R.string.condition_906
            951 -> R.string.condition_951
            952 -> R.string.condition_952
            953 -> R.string.condition_953
            954 -> R.string.condition_954
            955 -> R.string.condition_955
            956 -> R.string.condition_956
            957 -> R.string.condition_957
            958 -> R.string.condition_958
            959 -> R.string.condition_959
            960 -> R.string.condition_960
            961 -> R.string.condition_961
            962 -> R.string.condition_962
            else -> {
                Timber.e("Condition unknown $weatherId")
                R.string.condition_unknown
            }
        }
    }

    /**
     * Helper method to provide the art resource ID according to the weather condition ID returned
     * by the OpenWeatherMap call. This method is very similar to
     *
     * @param weatherId from OpenWeatherMap API response
     * See http://openweathermap.org/weather-conditions for a list of all IDs
     * @return resource ID for the corresponding icon. -1 if no relation is found.
     */
    fun getResourceIdForWeatherCondition(weatherId: Int): Int {
        return when (weatherId) {
            in 200..232 -> R.drawable.art_storm
            in 300..321 -> R.drawable.art_light_rain
            in 500..504 -> R.drawable.art_rain
            511 -> R.drawable.art_snow
            in 520..531 -> R.drawable.art_rain
            in 600..622 -> R.drawable.art_snow
            in 701..761 -> R.drawable.art_fog
            761, 771, 781 -> R.drawable.art_storm
            800 -> R.drawable.art_clear
            801 -> R.drawable.art_light_clouds
            in 802..804 -> R.drawable.art_clouds
            in 900..906 -> R.drawable.art_storm
            in 958..962 -> R.drawable.art_storm
            in 951..957 -> R.drawable.art_clear
            else -> {
                Timber.e("Unknown Weather: $weatherId")
                R.drawable.art_storm
            }
        }

    }
}