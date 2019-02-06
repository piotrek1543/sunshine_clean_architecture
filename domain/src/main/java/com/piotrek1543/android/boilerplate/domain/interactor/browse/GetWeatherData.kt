package com.piotrek1543.android.boilerplate.domain.interactor.browse

import com.piotrek1543.android.boilerplate.domain.executor.PostExecutionThread
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.interactor.FlowableUseCase
import com.piotrek1543.android.boilerplate.domain.model.WeatherData
import com.piotrek1543.android.boilerplate.domain.repository.WeatherDataRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [WeatherData] instances from the [WeatherDataRepository]
 */
open class GetWeatherData @Inject constructor(val weatherDataRepository: WeatherDataRepository,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread) :
        FlowableUseCase<WeatherData, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<WeatherData> {
        return weatherDataRepository.getWeatherData()
    }

}