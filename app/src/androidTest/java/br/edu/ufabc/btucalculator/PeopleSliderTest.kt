package br.edu.ufabc.btucalculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * check if people slider is displayed
 * check if people slider is discrete
 * check if people slider has correct initial value
 * check of people slider label is displayed and has correct initial value
 * check if people slider properly updates its label
 * check if people slider properly updates btu label
 */
@RunWith(AndroidJUnit4::class)
class PeopleSliderTest : SliderTestInterface {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val sliderTest = SliderTest(
        SliderUtil("Slide to change the quantity of people", "People", 2f, 20f, 1f),
        SliderLabelUtil("%d people", 2), "people_slider_label.csv", "people_slider_btu.csv"
    )

    @Before
    override fun setUp() = sliderTest.setUp()

    @Test
    override fun isDisplayed() = sliderTest.isDisplayed()

    @Test
    override fun hasCorrectBoundaries() = sliderTest.hasCorrectBoundaries()

    @Test
    override fun hasCorrectInitialValue() = sliderTest.labelHasCorrectInitialValue()

    @Test
    override fun labelHasCorrectInitialValue() = sliderTest.labelHasCorrectInitialValue()

    @Test
    override fun properlyUpdatesLabel() = sliderTest.properlyUpdatesLabel()

    @Test
    override fun properlyUpdatesBTU() = sliderTest.properlyUpdatesBTU()
}
