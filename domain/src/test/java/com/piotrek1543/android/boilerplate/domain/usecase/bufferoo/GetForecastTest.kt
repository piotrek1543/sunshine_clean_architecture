package com.piotrek1543.android.boilerplate.domain.usecase.bufferoo

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
    private lateinit var mockBufferooRepository: ForecastRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockBufferooRepository = mock()
        getForecast = GetForecast(mockBufferooRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getForecast.buildUseCaseObservable(null)
        verify(mockBufferooRepository).getForecast()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubBufferooRepositoryGetBufferoos(Flowable.just(ForecastFactory.makeForecast()))
        val testObserver = getForecast.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val bufferoos = ForecastFactory.makeForecast()
        stubBufferooRepositoryGetBufferoos(Flowable.just(bufferoos))
        val testObserver = getForecast.buildUseCaseObservable(null).test()
        testObserver.assertValue(bufferoos)
    }

    private fun stubBufferooRepositoryGetBufferoos(single: Flowable<Forecast>) {
        whenever(mockBufferooRepository.getForecast())
                .thenReturn(single)
    }

}