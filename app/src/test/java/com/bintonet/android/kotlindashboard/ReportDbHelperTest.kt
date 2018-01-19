package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.local.ReportDbHelper
import com.bintonet.android.kotlindashboard.model.local.Report
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Test class for the local SQLite DB
 * I am using Roboelectric for this since it allowd easy access to context, which is needed for DB interactions
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [21],
        packageName = "com.bintonet.android.kotlindashboard")
class ReportDbHelperTest {

    lateinit var reportDbHelper: ReportDbHelper

    @Before
    fun setup() {
        reportDbHelper = ReportDbHelper(RuntimeEnvironment.application)
        reportDbHelper.clearDbAndRecreate() // This is just to clear the db so we know what is there
    }

    /*
    test to ensure we can insert a row into the table
     */

    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {

        // Given
        val test_client_ref = "ABC-1234"
        val test_score = 123
        val report = Report(test_score, test_client_ref)

        // When
        reportDbHelper.insertReport(report)

        // Then
        assertEquals(reportDbHelper.getAllReport().size, 1)
        assertEquals(reportDbHelper.getAllReport().get(0).score, test_score)
        assertEquals(reportDbHelper.getAllReport().get(0).clientRef, test_client_ref)

    }

    /*
    test to ensure we can update a row
     */

    @Test
    @Throws(Exception::class)
    fun testDbUpdate() {

        // Given
        val testClientRef = "ABC-1234"
        val testScore = 123
        val report = Report(testScore, testClientRef)

        // When
        reportDbHelper.insertReport(report)

        // Then
        assertEquals(reportDbHelper.getAllReport().size, 1)
        assertEquals(reportDbHelper.getAllReport().get(0).score, testScore)
        assertEquals(reportDbHelper.getAllReport().get(0).clientRef, testClientRef)

        // Given
        val testScore2 = 321
        val report2 = Report(testScore2, testClientRef)
        // When
        reportDbHelper.updateReport(report2)

        // Then
        assertEquals(reportDbHelper.getAllReport().size, 1)
        assertEquals(reportDbHelper.getAllReport().get(0).score, testScore2)
        assertEquals(reportDbHelper.getAllReport().get(0).clientRef, testClientRef)


    }

    @After
    fun tearDown() {
        reportDbHelper.clearDb()
    }


}