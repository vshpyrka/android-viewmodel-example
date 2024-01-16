package com.example.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewModelSavedStateHandleActivityTest {

    @Test
    fun testViewModelSavedStateHandleActivity() {
        launchActivity<ViewModelSavedStateHandleActivity>().use {
            onView(withId(R.id.editText)).perform(click())
            onView(withId(R.id.editText)).perform(typeText("aco"))
            onView(withId(R.id.textView)).check(matches(withText("Acorn\nUbriaco\nVenaco")))
        }
    }

    @Test
    fun testSavedStateHandle() = runTest {
        val viewModel: SavedStateHandleViewModel

        val savedState = SavedStateHandle(
            mapOf(
                "query" to "aco"
            )
        )
        viewModel = SavedStateHandleViewModel(
            savedStateHandle = savedState
        )
        val result = viewModel.filteredData.first()
        assertTrue(
            result.contains("Acorn") &&
                    result.contains("Ubriaco") &&
                    result.contains("Venaco")
        )
    }
}
