package br.edu.ufabc.btucalculator

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import com.google.android.material.switchmaterial.SwitchMaterial
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

class DirectSunlightSwitchUtil {
    val description = "Enable if the room has direct sunlight"
    val text = "Direct sunlight"
    val matcher: Matcher<View> = AllOf.allOf(
        withContentDescription(description),
        instanceOf(SwitchMaterial::class.java)
    )

    fun setChecked(value: Boolean) = object : ViewAction {
        override fun getConstraints(): Matcher<View> =
            ViewMatchers.isAssignableFrom(SwitchMaterial::class.java)

        override fun getDescription(): String =
            "set switch value to $value"

        override fun perform(uiController: UiController?, view: View?) {
            (view as SwitchMaterial).isChecked = value
        }
    }

    val notFound
        get() = "Failed to find a Material Switch with the description '$description'"
    val notVisible
        get() = "Found a Material Switch with correct description but it is invisible"
    val incorrectDefaultValue
        get() = "Switch should be initially unchecked"
}
