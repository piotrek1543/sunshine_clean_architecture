package org.buffer.android.boilerplate.remote


import io.reactivex.Flowable
import org.buffer.android.boilerplate.remote.model.WeatherDataModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author piotr on 24.02.17.
 */

interface SunshineService {

    @GET("forecast")
    operator fun get(@Query("q") q: String,
                     @Query("mode") mode: String,
                     @Query("units") units: String,
                     @Query("type") type: String,
                     @Query("lang") lang: String,
                     @Query("appid") appId: String): Flowable<WeatherDataModel>
}