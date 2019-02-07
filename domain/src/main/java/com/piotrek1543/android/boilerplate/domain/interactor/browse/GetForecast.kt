package com.piotrek1543.android.boilerplate.domain.interactor.browse

import com.piotrek1543.android.boilerplate.domain.executor.PostExecutionThread
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.interactor.FlowableUseCase
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.domain.repository.ForecastRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Forecast] instances from the [ForecastRepository]
 */
open class GetForecast @Inject constructor(private val forecastRepository: ForecastRepository,
                                           threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread) :
        FlowableUseCase<Forecast, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<Forecast> {
        return forecastRepository.getForecast()
    }

}