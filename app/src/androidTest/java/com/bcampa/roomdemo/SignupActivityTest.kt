package com.bcampa.roomdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignupActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(InitialActivity::class.java)

    @Test
    fun signupActivityTest() {
        onView(withId(R.id.btnSignup)).perform(click())
        onView(withId(R.id.edtTxtName)).perform(typeText("Bruno"))
        onView(withId(R.id.edtTxtEmail)).perform(typeText("fake@email.com"))
        onView(withId(R.id.edtTxtPassword)).perform(typeText("senha"))
        onView(withId(R.id.edtTxtConfirmPassword)).perform(typeText("senha"))
        pressBack()
        onView(withId(R.id.edtTxtCpf)).perform(typeText("123.456.789-01"))
        pressBack()
        onView(withId(R.id.btnSignup)).perform(click())
        Thread.sleep(5000)
    }
}
