package com.piotrek1543.android.boilerplate.ui.forecast

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ForecastActivityTest {

    // An Idling Resource that waits for Data Binding to have no pending bindings
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        //IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        //IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun checkIfForecastListIsScrollableAndItemsAreInflatedCorrectly() {
        // start up Forecast screen
        ActivityScenario.launch(ForecastActivity::class.java)

        for (index in 0..12) {
            onView(withId(R.id.recycler_browse))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
        }
    }

    fun checkIfForecastItemHasCorrectViewIdies() {
        // start up Forecast screen
        ActivityScenario.launch(ForecastActivity::class.java)

        for (viewId in testIdiesList)
            onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(0))
                    .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withId(viewId))))
    }

    fun shouldShowToastOnItemClick() {
        // start up Forecast screen
        ActivityScenario.launch(ForecastActivity::class.java)

        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(0)).perform(click())

    }

    companion object {
        val testIdiesList = listOf(
                R.id.text_date,
                R.id.text_high_temperature,
                R.id.text_low_temperature,
                R.id.text_weather_description,
                R.id.image_weather_icon
        )
    }
}