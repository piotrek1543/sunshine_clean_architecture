package com.piotrek1543.android.boilerplate.cache

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.mapper.*
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import com.piotrek1543.android.boilerplate.cache.test.factory.ForecastFactory
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastCacheImplTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private var forecastsDatabase = Room.inMemoryDatabaseBuilder(context,
            SunshineDatabase::class.java).allowMainThreadQueries().build()
    private var preferencesHelper = PreferencesHelper(context)

    private val forecastEntityMapper = ForecastEntityMapper()
    private val cityEntityMapper = CityEntityMapper()
    private val coordEntityMapper = CoordEntityMapper()
    private val listEntityMapper = ListEntityMapper()
    private val cloudsEntityMapper = CloudsEntityMapper()
    private val mainEntityMapper = MainEntityMapper()
    private val podEntityMapper = PodEntityMapper()
    private val rainEntityMapper = RainEntityMapper()
    private val snowEntityMapper = SnowEntityMapper()
    private val windEntityMapper = WindEntityMapper()
    private val weatherEntityMapper = WeatherEntityMapper()

    private val databaseHelper = ForecastCacheImpl(
            sunshineDatabase = forecastsDatabase,
            cityEntityMapper = cityEntityMapper,
            cloudsEntityMapper = cloudsEntityMapper,
            coordEntityMapper = coordEntityMapper,
            forecastEntityMapper = forecastEntityMapper,
            listEntityMapper = listEntityMapper,
            mainEntityMapper = mainEntityMapper,
            podEntityMapper = podEntityMapper,
            rainEntityMapper = rainEntityMapper,
            snowEntityMapper = snowEntityMapper,
            windEntityMapper = windEntityMapper,
            weatherEntityMapper = weatherEntityMapper,
            preferencesHelper = preferencesHelper
    )

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearForecast().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Forecast">
    @Test
    fun saveForecastCompletes() {
        val forecastEntity = ForecastFactory.makeForecastEntity()

        val testObserver = databaseHelper.saveForecast(forecastEntity).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveForecastSavesData() {
        val forecastEntitiy = ForecastFactory.makeForecastEntity()
        databaseHelper.saveForecast(forecastEntitiy).test()
        checkNumRowsInForecastTable(1)
    }
    //</editor-fold>

    //<editor-fold desc="Get Forecast">
    @Test
    fun getForecastCompletes() {
        val testObserver = databaseHelper.getForecast().test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsNotCompletedData() {
        val forecastEntity = ForecastFactory.makeForecastEntity()
        val cachedForecast = mutableListOf<CachedForecast>()
        cachedForecast.add(forecastEntityMapper.mapToCached(forecastEntity))
        insertForecast(cachedForecast)

        val testObserver = databaseHelper.getForecast().test()
        //FIXME: add missing City, Snow, Rain, saving implementation
        testObserver.assertNever(forecastEntity)
    }
    //</editor-fold>

    private fun insertForecast(cachedForecast: List<CachedForecast>) {
        cachedForecast.forEach {
            forecastsDatabase.cachedForecastDao().insertForecast(it)
        }
    }

    private fun checkNumRowsInForecastTable(expectedRows: Int) {
        val numberOfRows = if (forecastsDatabase.cachedForecastDao().getForecast() == null) 0 else 1
        assertEquals(expectedRows, numberOfRows)
    }

}