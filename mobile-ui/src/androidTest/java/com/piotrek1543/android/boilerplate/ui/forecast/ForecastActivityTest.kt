package com.piotrek1543.android.boilerplate.ui.forecast

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.piotrek1543.android.boilerplate.ui.R
import com.piotrek1543.android.boilerplate.ui.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
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
        val activityScenario = ActivityScenario.launch(ForecastActivity::class.java)

        for (index in 5 downTo 1) {
            onView(withId(R.id.recycler_browse))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
        }

        activityScenario.close()
    }

    @Test
    fun checkIfForecastItemHasCorrectViewIdies() {
        // start up Forecast screen
        val activityScenario = ActivityScenario.launch(ForecastActivity::class.java)

        for (viewId in testForecastIdiesList)
            onView(withId(R.id.recycler_browse))
                    .perform(RecyclerViewActions
                            .scrollToPosition<RecyclerView.ViewHolder>(0))
                    .check(matches(hasDescendant(withId(viewId))))

        activityScenario.close()
    }

    @Test
    fun shouldShowToastOnItemClick() {
        // start up Forecast screen
        val activityScenario = ActivityScenario.launch(ForecastActivity::class.java)

        var forecastActivity: ForecastActivity? = null

        activityScenario.onActivity {
            forecastActivity = it
        }

        onView(withId(R.id.recycler_browse))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<ForecastAdapter.ViewHolder>(0, click()))

        onView(withText(containsString("ForecastViewModel")))
                .inRoot(RootMatchers.withDecorView(not(`is`(forecastActivity!!.window.decorView))))
                .check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun shouldContextMenuContainsTwoItems() {
        // start up Forecast screen
        val activityScenario = ActivityScenario.launch(ForecastActivity::class.java)

        openActionBarOverflowOrOptionsMenu(getInstrumentation().context)

        for (itemId in testSettingsIdiesList)
            onView(withId(itemId)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    companion object {
        val testForecastIdiesList = listOf(
                R.id.text_date,
                R.id.text_high_temperature,
                R.id.text_low_temperature,
                R.id.text_weather_description,
                R.id.image_weather_icon
        )

        val testSettingsIdiesList = listOf(
                R.id.action_map,
                R.id.action_settings
        )
    }
}