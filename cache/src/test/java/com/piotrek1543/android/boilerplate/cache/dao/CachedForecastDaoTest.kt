package com.piotrek1543.android.boilerplate.cache.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.test.factory.ForecastFactory
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class CachedForecastDaoTest {

    private lateinit var sunshineDatabase: SunshineDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        sunshineDatabase = Room.inMemoryDatabaseBuilder(
                context, SunshineDatabase::class.java).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        sunshineDatabase.close()
    }

    @Test
    fun insertForecastsSavesData() {
        val cachedForecast = ForecastFactory.makeCachedForecast()
        sunshineDatabase.cachedForecastDao().insertForecast(cachedForecast)

        val forecast = sunshineDatabase.cachedForecastDao().getForecast()
        assertNotNull(forecast)
    }

    @Test
    fun getForecastsRetrievesData() {
        val cachedForecast = ForecastFactory.makeCachedForecast()
        sunshineDatabase.cachedForecastDao().insertForecast(cachedForecast)

        val retrievedForecast = sunshineDatabase.cachedForecastDao().getForecast()!!
        assertSame(retrievedForecast.id, cachedForecast.id)
    }

}