package com.bintonet.android.kotlindashboard

import android.widget.TextView
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import android.app.Activity
import org.robolectric.android.controller.ActivityController

import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent


/**
 * Standard tests for the MainActivity Class
 * 
 */

class MainActivityTest : BaseTest(){

    lateinit var mainActivity:MainActivity

    @Before
    fun init(){
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun checkTextViewString_presentOrNot(){
        val textView=mainActivity.findViewById<TextView>(R.id.tv_score)
        val stringValue=textView.text.toString()
        assertThat(stringValue,equalTo(""))
    }

//    @Test
//    fun testShouldInflateLayout() {
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        val tested = spy(activity)
//        tested.onCreate(null)
//        verify(tested).setContentView(R.layout.activity_main)
//        val textView = activity.findViewById(R.id.tv_score) as TextView
//
//    }


}