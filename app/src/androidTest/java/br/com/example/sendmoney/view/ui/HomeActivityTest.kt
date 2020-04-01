package br.com.example.sendmoney.view.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.example.sendmoney.R
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    var homeActivityTestRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun registerIdlingResource() {
        //https://medium.com/azimolabs/wait-for-it-idlingresource-and-conditionwatcher-602055f32356#.vsyeol9wp
        IdlingRegistry.getInstance().register(homeActivityTestRule.activity.getCountingIdlingResource())

        //Prepare your test fixture for this test. In this case we register an IdlingResources with
        //Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
        //idle state. This helps Espresso to synchronize your test actions, which makes tests significantly
        //more reliable.
    }

    @After
    fun unregisterIdlingResource() {
        //Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
        IdlingRegistry.getInstance().unregister(homeActivityTestRule.activity.getCountingIdlingResource())
    }

    //onView(withId(R.id.pbLoading)).check(matches(not(isDisplayed())))
    @Test
    fun graphicalElements_AreDisplayed() {
        onView(withId(R.id.pbLoading)).check(matches(isDisplayed()))
        onView(withId(R.id.ivUserPicture)).check(matches(isDisplayed()))

        onView(withId(R.id.tvUserName)).check(matches(withText(containsString("Bedrick Prokop"))))
        onView(withId(R.id.tvUserEmail)).check(matches(withText(containsString("bedrick.prokop@gmail.com"))))

        onView(withId(R.id.mbSendMoney)).check(matches(isDisplayed()))

        //TODO remove not
        onView(withId(R.id.mbShowHistory)).check(matches(not(isDisplayed())))
    }

    @Test
    fun btSendMoney_OpensSendMoneyActivity() {
        onView(withId(R.id.mbSendMoney)).perform(click())
        onView(withText("SEND MONEY")).check(matches(isDisplayed()))
    }

}