package org.buffer.android.boilerplate.domain.interactor.browse

import io.reactivex.Flowable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.interactor.FlowableUseCase
import org.buffer.android.boilerplate.domain.model.WeatherData
import org.buffer.android.boilerplate.domain.repository.WeatherDataRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [WeatherData] instances from the [WeatherDataRepository]
 */
open class GetWeatherData @Inject constructor(val weatherDataRepository: WeatherDataRepository,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread):
        FlowableUseCase<WeatherData, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<WeatherData> {
        return weatherDataRepository.getWeatherData()
    }

}