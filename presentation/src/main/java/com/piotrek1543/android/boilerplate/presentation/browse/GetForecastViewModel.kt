package com.piotrek1543.android.boilerplate.presentation.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotrek1543.android.boilerplate.domain.interactor.browse.GetForecast
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.presentation.data.Resource
import com.piotrek1543.android.boilerplate.presentation.data.ResourceState
import com.piotrek1543.android.boilerplate.presentation.mapper.ForecastMapper
import com.piotrek1543.android.boilerplate.presentation.model.ForecastView
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber
import javax.inject.Inject

open class GetForecastViewModel @Inject internal constructor(
        private val getForecast: GetForecast,
        private val forecastMapper: ForecastMapper) : ViewModel() {

    private val forecast: MutableLiveData<Resource<List<ForecastView>>?> =
            MutableLiveData()

    init {
        fetchForecast()
    }

    override fun onCleared() {
        getForecast.dispose()
        super.onCleared()
    }

    fun getForecast(): LiveData<Resource<List<ForecastView>>?> {
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
                    t.list?.map { forecastMapper.mapToView(it) }, null))
        }

        override fun onError(exception: Throwable) {
            forecast.postValue(Resource(ResourceState.ERROR, null, exception.message))
            Timber.d(exception)
        }

    }

}