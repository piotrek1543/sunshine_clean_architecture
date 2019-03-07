package com.piotrek1543.android.boilerplate.ui.forecast

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ForecastActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule<ForecastActivity>(ForecastActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubBufferooRepositoryGetBufferoos(Flowable.just(BufferooFactory.makeBufferooList(2)))
        activity.launchActivity(null)
    }

    @Test
    fun bufferoosDisplay() {
        val bufferoos = BufferooFactory.makeBufferooList(1)
        stubBufferooRepositoryGetBufferoos(Flowable.just(bufferoos))
        activity.launchActivity(null)

        checkBufferooDetailsDisplay(bufferoos[0], 0)
    }

    @Test
    fun bufferoosAreScrollable() {
        val bufferoos = BufferooFactory.makeBufferooList(20)
        stubBufferooRepositoryGetBufferoos(Flowable.just(bufferoos))
        activity.launchActivity(null)

        bufferoos.forEachIndexed { index, bufferoo ->
            onView(withId(R.id.recycler_browse)).perform(RecyclerViewActions.
                    scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(index))
            checkBufferooDetailsDisplay(bufferoo, index) }
    }

    private fun checkBufferooDetailsDisplay(bufferoo: Bufferoo, position: Int) {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(position))
                .check(matches(hasDescendant(withText(bufferoo.name))))
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(position))
                .check(matches(hasDescendant(withText(bufferoo.title))))
    }

    private fun stubBufferooRepositoryGetBufferoos(single: Flowable<List<Bufferoo>>) {
        whenever(TestApplication.appComponent().bufferooRepository().getBufferoos())
                .thenReturn(single)
    }

}