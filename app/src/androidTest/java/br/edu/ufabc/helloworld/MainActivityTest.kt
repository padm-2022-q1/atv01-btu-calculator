package br.edu.ufabc.helloworld

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun helloWorldIsCorrect() {
        val expected = "Hello, UFABC!"
        onView(withText(expected))
            .withFailureHandler(newHandler("Could not find a TextView with the text '$expected'"))
            .check(matches(isDisplayed()))
            .withFailureHandler(newHandler("Found a TextView with text $expected, but is not being displayed"))
    }
}
