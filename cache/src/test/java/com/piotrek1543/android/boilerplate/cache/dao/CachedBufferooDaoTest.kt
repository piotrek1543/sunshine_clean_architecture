package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Room
import org.buffer.android.boilerplate.cache.db.SunshineDatabase
import org.buffer.android.boilerplate.cache.test.factory.BufferooFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
open class CachedBufferooDaoTest {

    private lateinit var sunshineDatabase: SunshineDatabase

    @Before
    fun initDb() {
        sunshineDatabase = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
                SunshineDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        sunshineDatabase.close()
    }

    @Test
    fun insertBufferoosSavesData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        sunshineDatabase.cachedBufferooDao().insertBufferoo(cachedBufferoo)

        val bufferoos = sunshineDatabase.cachedBufferooDao().getBufferoos()
        assert(bufferoos.isNotEmpty())
    }

    @Test
    fun getBufferoosRetrievesData() {
        val cachedBufferoos = BufferooFactory.makeCachedBufferooList(5)

        cachedBufferoos.forEach {
            sunshineDatabase.cachedBufferooDao().insertBufferoo(it) }

        val retrievedBufferoos = sunshineDatabase.cachedBufferooDao().getBufferoos()
        assert(retrievedBufferoos == cachedBufferoos.sortedWith(compareBy({ it.id }, { it.id })))
    }

}