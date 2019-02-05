package org.buffer.android.boilerplate.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.subscribers.DisposableSubscriber
import org.buffer.android.boilerplate.domain.interactor.browse.GetWeatherData
import org.buffer.android.boilerplate.domain.model.WeatherData
import org.buffer.android.boilerplate.presentation.data.Resource
import org.buffer.android.boilerplate.presentation.data.ResourceState
import org.buffer.android.boilerplate.presentation.mapper.WeatherDataMapper
import org.buffer.android.boilerplate.presentation.model.WeatherDataView
import javax.inject.Inject

open class GetWeatherDataViewModel @Inject internal constructor(
        private val getWeatherData: GetWeatherData,
        private val weatherDataMapper: WeatherDataMapper) : ViewModel() {

    private val weatherDataLiveData: MutableLiveData<Resource<WeatherDataView>> =
            MutableLiveData()

    init {
        fetchWeatherData()
    }

    override fun onCleared() {
        getWeatherData.dispose()
        super.onCleared()
    }

    fun getWeatherData(): LiveData<Resource<WeatherDataView>> {
        return weatherDataLiveData
    }

    fun fetchWeatherData() {
        weatherDataLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getWeatherData.execute(WeatherDataSubscriber())
    }

    inner class WeatherDataSubscriber: DisposableSubscriber<WeatherData>() {

        override fun onComplete() { }

        override fun onNext(t: WeatherData) {
            weatherDataLiveData.postValue(Resource(ResourceState.SUCCESS,
                     weatherDataMapper.mapToView(t) , null))
        }

        override fun onError(exception: Throwable) {
            weatherDataLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

}