package br.edu.ufabc.btucalculator

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.slider.Slider
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

/**
 * Utilities to operate a slider.
 */
class SliderUtil(
    private val contentDescription: String,
    private val name: String,
    val from: Float,
    val to: Float,
    val stepSize: Float
) {

    val matcher: Matcher<View> = AllOf.allOf(
        ViewMatchers.withContentDescription(contentDescription),
        CoreMatchers.instanceOf(Slider::class.java)
    )

    companion object {
        fun withValue(value: Float) = object : BoundedMatcher<View, Slider>(Slider::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("slider should have value $value")
            }

            override fun matchesSafely(slider: Slider?): Boolean =
                slider?.let { slider.value == value } ?: false
        }

        fun setValue(value: Float) = object : ViewAction {
            override fun getConstraints(): Matcher<View> =
                ViewMatchers.isAssignableFrom(Slider::class.java)

            override fun getDescription(): String =
                "set slider value to $value"

            override fun perform(uiController: UiController?, slider: View?) {
                try {
                    (slider as Slider).value = value
                } catch (e: Exception) {
                    throw PerformException.Builder().withCause(e.cause).build()
                }
            }
        }
    }

    val notFound: String
        get() = "Could not find a Slider with content description \"$contentDescription\""
    val notVisible
        get() = "'$name' Slider was found but it is not visible"
    val outOfBounds
        get() = "'$name' Slider should accept values from $from to $to with step $stepSize"
    val incorrectInitial
        get() = "'$name' Slider should have $from as initial value"
    val changeFailed
        get() = "Failed to change the value of the '$name' slider"
    val inconsistentLabel
        get() = "'$name' Slider and its TextView label have inconsistent values."
    val inconsistentBtu
        get() = "'$name' Slider and the BTU TextView have inconsistent values."

    fun boundaries() =
        object : BoundedMatcher<View, Slider>(Slider::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("slider should have boundaries 'from'=$from and 'to'=$to")
            }

            override fun matchesSafely(slider: Slider?): Boolean {
                val fromCheck = slider?.let { slider.valueFrom == from } ?: false
                val toCheck = slider?.let { slider.valueTo == to } ?: false
                val stepCheck = slider?.let { slider.stepSize == stepSize } ?: false

                return fromCheck && toCheck && stepCheck
            }
        }
}
