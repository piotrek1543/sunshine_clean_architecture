package com.piotrek1543.android.boilerplate.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.piotrek1543.android.boilerplate.domain.interactor.browse.GetForecast
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.presentation.data.Resource
import com.piotrek1543.android.boilerplate.presentation.data.ResourceState
import com.piotrek1543.android.boilerplate.presentation.mapper.ForecastMapper
import com.piotrek1543.android.boilerplate.presentation.model.ForecastView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class GetForecastViewModel @Inject internal constructor(
        private val getForecast: GetForecast,
        private val forecastMapper: ForecastMapper) : ViewModel() {

    private val forecast: MutableLiveData<Resource<ForecastView>> =
            MutableLiveData()

    init {
        fetchForecast()
    }

    override fun onCleared() {
        getForecast.dispose()
        super.onCleared()
    }

    fun getForecast(): LiveData<Resource<ForecastView>> {
        return forecast
    }

    fun fetchForecast() {
        forecast.postValue(Resource(ResourceState.LOADING, null, null))
        return getForecast.execute(ForecastSubscriber())
    }

    inner class ForecastSubscriber : DisposableSubscriber<Forecast>() {

        override fun onComplete() {}

        override fun onNext(t: Forecast) {
            forecast.postValue(Resource(ResourceState.SUCCESS,
                    forecastMapper.mapToView(t), null))
        }

        override fun onError(exception: Throwable) {
            forecast.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

}