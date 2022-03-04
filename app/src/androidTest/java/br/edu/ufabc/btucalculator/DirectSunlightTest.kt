package br.edu.ufabc.btucalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.switchmaterial.SwitchMaterial
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * check if direct sunlight switch has correct description
 * check if direct sunlight switch has correct text
 * check if direct sunlight switch has correct default value
 * check if direct sunlight switch properly updates btu label
 */
@RunWith(AndroidJUnit4::class)
class DirectSunlightTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val switchUtil = DirectSunlightSwitchUtil()

    @Before
    fun setUp() {
        Espresso.setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation()))
    }

    @Test
    fun hasCorrectContentDescription() {
        try {
            onView(switchUtil.matcher)
                .check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            Assert.fail(switchUtil.notFound)
        } catch (e: AssertionFailedError) {
            Assert.fail(switchUtil.notVisible)
        }
    }

    @Test
    fun hasCorrectText() {
        try {
            onView(
                AllOf.allOf(
                    withText(switchUtil.text),
                    instanceOf(SwitchMaterial::class.java)
                )
            )
                .check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            Assert.fail(switchUtil.notFound)
        } catch (e: AssertionFailedError) {
            Assert.fail(switchUtil.notVisible)
        }
    }

    @Test
    fun hasCorrectDefaultValue() {
        try {
            onView(
                AllOf.allOf(
                    withContentDescription(switchUtil.description),
                    instanceOf(SwitchMaterial::class.java)
                )
            )
                .check(matches(isNotChecked()))
        } catch (e: NoMatchingViewException) {
            Assert.fail(switchUtil.notFound)
        } catch (e: AssertionFailedError) {
            Assert.fail(switchUtil.incorrectDefaultValue)
        }
    }

    @Test
    fun properlyUpdatesBTU() {
        val valueOff = "2400"
        val valueOn = "3200"

        try {
            onView(BTUTextViewUtil.withValue(valueOff)).check(matches(isDisplayed()))
            onView(
                AllOf.allOf(
                    withContentDescription(switchUtil.description),
                    instanceOf(SwitchMaterial::class.java)
                )
            )
                .perform(click())
            onView(BTUTextViewUtil.withValue(valueOn)).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            Assert.fail("Switch is not properly updating the BTU TextView. ${e.message}")
        } catch (e: AssertionFailedError) {
            Assert.fail("Switch is not properly updating the BTU TextView. ${e.message}")
        } catch (e: PerformException) {
            Assert.fail("A click on the switch failed.")
        }
    }
}
