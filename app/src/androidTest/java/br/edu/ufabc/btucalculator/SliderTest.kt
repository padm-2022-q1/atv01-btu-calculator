package br.edu.ufabc.btucalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.AssertionFailedError
import org.junit.Assert.fail

/**
 * General framework to test a slider.
 */

class SliderTest(
    private val sliderUtil: SliderUtil,
    private val sliderLabelUtil: SliderLabelUtil,
    private val sliderLabelFile: String,
    private val sliderBtuFile: String
) : SliderTestInterface {

    override fun setUp() {
        Espresso.setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation()))
    }

    override fun isDisplayed() {
        try {
            onView(sliderUtil.matcher)
                .check(matches(ViewMatchers.isDisplayed()))
        } catch (e: NoMatchingViewException) {
            fail(sliderUtil.notFound)
        } catch (e: AssertionFailedError) {
            fail(sliderUtil.notVisible)
        }
    }

    override fun hasCorrectBoundaries() {
        try {
            onView(sliderUtil.matcher)
                .check(matches(sliderUtil.boundaries()))
        } catch (e: NoMatchingViewException) {
            fail(sliderUtil.notFound)
        } catch (e: AssertionFailedError) {
            fail(sliderUtil.outOfBounds)
        }
    }

    override fun hasCorrectInitialValue() {
        try {
            onView(sliderUtil.matcher).check(matches(SliderUtil.withValue(sliderUtil.from)))
        } catch (e: NoMatchingViewException) {
            fail(sliderUtil.notFound)
        } catch (e: AssertionFailedError) {
            fail(sliderUtil.incorrectInitial)
        }
    }

    override fun labelHasCorrectInitialValue() {
        try {
            onView(withText(sliderLabelUtil.initialFormatted)).check(matches(ViewMatchers.isDisplayed()))
        } catch (e: NoMatchingViewException) {
            fail(sliderLabelUtil.incorrectDefaultText)
        }
    }

    override fun properlyUpdatesLabel() {
        try {
            InstrumentationRegistry.getInstrumentation().targetContext.resources
                .assets.open(sliderLabelFile).bufferedReader().lines().forEach {
                    val (sliderValue, labelValue) = it.split(";")

                    onView(sliderUtil.matcher).perform(SliderUtil.setValue(sliderValue.toFloat()))
                    onView(withText(labelValue)).check(matches(ViewMatchers.isDisplayed()))
                }
        } catch (e: PerformException) {
            fail(sliderUtil.changeFailed)
        } catch (e: AssertionFailedError) {
            fail("${sliderUtil.inconsistentLabel} ${e.message}")
        }
    }

    override fun properlyUpdatesBTU() {
        try {
            InstrumentationRegistry.getInstrumentation().targetContext.resources
                .assets.open(sliderBtuFile).bufferedReader().lines().forEach {
                    val (sliderValue, btuValue) = it.split(";")

                    onView(sliderUtil.matcher).perform(SliderUtil.setValue(sliderValue.toFloat()))
                    onView(BTUTextViewUtil.withValue(btuValue)).check(matches(ViewMatchers.isDisplayed()))
                }
        } catch (e: Exception) {
            when (e) {
                is PerformException -> fail(sliderUtil.changeFailed)
                is NoMatchingViewException -> fail("${sliderUtil.inconsistentBtu}. ${e.message}")
                else -> throw e
            }
        } catch (e: AssertionFailedError) {
            fail("BTU TextView found to be correct but it is not visible")
        }
    }
}
