package com.example.viewmodel

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewModelActivitiesTest {

    private lateinit var scenario: ActivityScenario<ViewModelActivities>

    @Before
    fun setUp() {
        scenario = launchActivity()
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun testOpenViewModelFactory() {
        Espresso.onView(ViewMatchers.withId(R.id.view_model_factory)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ViewModelFactoryActivity::class.java.name))
    }

    @Test
    fun testOpenScopedViewModel() {
        Espresso.onView(ViewMatchers.withId(R.id.scoped_view_model)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ScopedViewModelActivity::class.java.name))
    }

    @Test
    fun testOpenViewModelSavedStateHandle() {
        Espresso.onView(ViewMatchers.withId(R.id.saved_state_handle)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ViewModelSavedStateHandleActivity::class.java.name))
    }
}
