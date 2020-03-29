package br.com.example.sendmoney.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import java.util.concurrent.atomic.AtomicInteger

object IdleResourceUtil {

    //Idle: 0 - Busy: 1 or more
    private const val RESOURCE = "GLOBAL"
    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource = mCountingIdlingResource

    //to became busy
    fun increment() = mCountingIdlingResource.increment()

    //to became idle
    fun decrement() = mCountingIdlingResource.decrement()

    internal class SimpleCountingIdlingResource(private val mResourceName: String) :
        IdlingResource {

        private val counter = AtomicInteger(0)

        // written from main thread, read from any thread.
        @Volatile
        private var resourceCallback: ResourceCallback? = null

        override fun getName(): String = mResourceName

        override fun isIdleNow(): Boolean = counter.get() == 0

        override fun registerIdleTransitionCallback(callback: ResourceCallback) {
            resourceCallback = callback
        }

        //Increments the count of in-flight transactions to the resource being monitored.
        fun increment() = counter.getAndIncrement()

        //Decrements the count of in-flight transactions to the resource being monitored.
        //If this operation results in the counter falling below 0 - an exception is raised.
        //throws IllegalStateException if the counter is below 0.
        fun decrement() {
            val counterVal = counter.decrementAndGet()
            if (counterVal == 0) {
                // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
                if (null != resourceCallback) {
                    resourceCallback!!.onTransitionToIdle()
                }
            } else check(counterVal >= 0) { "Counter has been corrupted!" }
        }
    }
}