package br.edu.ufabc.helloworld

import android.content.Context
import android.view.View
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.base.DefaultFailureHandler
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher

class CustomFailureHandler(message: String, context: Context) : FailureHandler {
    private val delegate: FailureHandler
    private val message: String

    init {
        delegate = DefaultFailureHandler(context)
        this.message = message
    }

    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {
        try {
            delegate.handle(error, viewMatcher)
        } catch (e: NoMatchingViewException) {
            throw Exception(message).initCause(
                NoMatchingViewException.Builder().from(e)
                    .includeViewHierarchy(false).build()
            )
//            throw NoMatchingViewException.Builder().from(e).withCause(Exception(message)).build()
        }
    }
}

fun newHandler(message: String) =
    CustomFailureHandler(message, InstrumentationRegistry.getInstrumentation().targetContext)
