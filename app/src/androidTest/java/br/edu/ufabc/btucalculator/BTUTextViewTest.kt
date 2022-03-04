package br.edu.ufabc.btucalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.AssertionFailedError
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BTUTextViewTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Espresso.setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation()))
    }

    @Test
    fun hasCorrectInitialValue() {
        val initialValue = "2400"

        try {
            onView(BTUTextViewUtil.withValue(initialValue)).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            fail("Failed to find a TextView with the value $initialValue")
        } catch (e: AssertionFailedError) {
            fail("Found the BTU TextView but it is invisible")
        }
    }
}
