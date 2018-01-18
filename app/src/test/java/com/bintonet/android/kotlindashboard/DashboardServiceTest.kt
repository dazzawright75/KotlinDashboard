package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.api.DashboardService
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

/**
 * Standard tests for the DashboardService (testing retrofit)
 */

class DashboardServiceTest {

    lateinit var service: DashboardService

    @Before
    internal fun setUp() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(DashboardService::class.java)
    }

    @Test
    internal fun should_callServiceWithCoroutine() {
        runBlocking {
            val dashboardValues = service.getDashboardValues().await()
            assertNotNull(dashboardValues)
            assertNotNull(dashboardValues.creditReportInfo)
            assertNotNull(dashboardValues.creditReportInfo?.score)
        }
    }
}