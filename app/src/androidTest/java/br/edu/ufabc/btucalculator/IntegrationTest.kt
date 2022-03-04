package br.edu.ufabc.btucalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
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

/**
 * Test all components in coordination
 */
@RunWith(AndroidJUnit4::class)
class IntegrationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val integrationTestFile = "integration.csv"


    @Before
    fun setUp() {
        Espresso.setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation()))
    }

    @Test
    fun allTests() {
        InstrumentationRegistry.getInstrumentation().targetContext.resources
            .assets.open(integrationTestFile).bufferedReader().lines().forEach {
                val (people, area, sunlight, btu) = it.split(";")

                try {
                    onView(areaSliderUtil.matcher).perform(SliderUtil.setValue(area.toFloat()))
                    onView(peopleSliderUtil.matcher).perform(SliderUtil.setValue(people.toFloat()))
                    onView(sunlightSwitchUtil.matcher).perform(sunlightSwitchUtil.setChecked(sunlight == "yes"))
                    onView(BTUTextViewUtil.withValue(btu)).check(matches(isDisplayed()))
                } catch (e: NoMatchingViewException) {
                    fail("Some component was not found during integration test: ${e.message}")
                } catch (e: PerformException) {
                    fail("Failed to perform some action during integration test: ${e.message}")
                } catch (e: AssertionFailedError) {
                    fail("BTU value does not match expected value: ${e.message}")
                }
            }
    }
}
