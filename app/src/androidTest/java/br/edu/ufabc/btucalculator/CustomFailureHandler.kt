package br.edu.ufabc.btucalculator

import android.app.Instrumentation
import android.view.View
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.base.DefaultFailureHandler
import org.hamcrest.Matcher

/**
 * A handler that disables view hierarchy logging.
 */
class CustomFailureHandler(instrumentation: Instrumentation) : FailureHandler {
    private val delegate: FailureHandler

    init {
        delegate = DefaultFailureHandler(instrumentation.targetContext)
    }

    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {
        try {
            delegate.handle(error, viewMatcher)
        } catch (e: NoMatchingViewException) {
            throw NoMatchingViewException.Builder().from(e)
                .includeViewHierarchy(false).build()
        }
    }
}
