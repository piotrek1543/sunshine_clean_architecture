package com.piotrek1543.android.boilerplate.domain.usecase.Forecast

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.piotrek1543.android.boilerplate.domain.executor.PostExecutionThread
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.interactor.browse.GetForecast
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.domain.repository.ForecastRepository
import com.piotrek1543.android.boilerplate.domain.test.factory.ForecastFactory
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class GetForecastTest {

    private lateinit var getForecast: GetForecast

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockForecastRepository: ForecastRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockForecastRepository = mock()
        getForecast = GetForecast(
                mockForecastRepository,
                mockThreadExecutor,
                mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getForecast.buildUseCaseObservable(null)
        verify(mockForecastRepository).getForecast()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubForecastRepositoryGetForecasts(Flowable.just(ForecastFactory.makeForecast()))
        val testObserver = getForecast.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val Forecasts = ForecastFactory.makeForecast()
        stubForecastRepositoryGetForecasts(Flowable.just(Forecasts))
        val testObserver = getForecast.buildUseCaseObservable(null).test()
        testObserver.assertValue(Forecasts)
    }

    private fun stubForecastRepositoryGetForecasts(single: Flowable<Forecast>) {
        whenever(mockForecastRepository.getForecast())
                .thenReturn(single)
    }

}