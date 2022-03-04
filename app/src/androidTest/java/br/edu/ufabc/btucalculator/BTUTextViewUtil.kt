package br.edu.ufabc.btucalculator

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class BTUTextViewUtil {
    companion object {
        fun withValue(value: String) =
            object : BoundedMatcher<View, TextView>(TextView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("'BTU' TextView should have value $value")
                }

                override fun matchesSafely(btuTextView: TextView?): Boolean =
                    try {
                        btuTextView?.let { value.toFloat() == it.text.toString().trim().toFloat() } ?: false
                    } catch (e: NumberFormatException) {
                        false
                    }
            }
    }
}
