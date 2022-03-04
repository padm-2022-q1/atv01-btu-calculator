package br.edu.ufabc.btucalculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * check if area slider is displayed
 * check if area slider is continuous
 * check if area slider has correct initial value
 * check if area slider label is displayed and has correct initial value
 * check if area slider properly updates its label
 * check if area slider properly updates btu label
 */
@RunWith(AndroidJUnit4::class)
class AreaSliderTest : SliderTestInterface {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val sliderTest = SliderTest(
        areaSliderUtil,
        SliderLabelUtil("%d m^2", 2), "area_slider_label.csv", "area_slider_btu.csv"
    )

    @Before
    override fun setUp() = sliderTest.setUp()

    @Test
    override fun isDisplayed() = sliderTest.isDisplayed()

    @Test
    override fun hasCorrectBoundaries() = sliderTest.hasCorrectBoundaries()

    @Test
    override fun hasCorrectInitialValue() = sliderTest.hasCorrectInitialValue()

    @Test
    override fun labelHasCorrectInitialValue() = sliderTest.labelHasCorrectInitialValue()

    @Test
    override fun properlyUpdatesLabel() = sliderTest.properlyUpdatesLabel()

    @Test
    override fun properlyUpdatesBTU() = sliderTest.properlyUpdatesBTU()
}
