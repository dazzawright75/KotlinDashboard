package com.bintonet.android.kotlindashboard

import android.app.Application
import android.content.Context
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File

/**
 * Created by darren.w.wright on 21/01/2018.
 */
/**
 * Base class for Robolectric data layer tests.
 * Inherit from this class to create a test.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [21],
        application = BaseTest.ApplicationStub::class,
        packageName = "com.bintonet.android.kotlindashboard")
abstract class BaseTest {

    fun context(): Context {
        return RuntimeEnvironment.application
    }

    fun cacheDir(): File {
        return context().cacheDir
    }

    internal class ApplicationStub : Application()
}