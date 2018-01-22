package com.bintonet.android.kotlindashboard

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

/**
 * Standard tests for the DashboardService (testing retrofit)
 */

class DashboardServiceTest {

    lateinit var api: DashboardDataSource

    var BASE_URL: String = "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/"

    @Before
    internal fun setUp() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.api = retrofit.create(DashboardDataSource::class.java)

    }

    @Test
    internal fun should_callServiceWithCoroutine() {
        runBlocking {
            val dashboardValues = api.dashboardValues
            assertNotNull(dashboardValues)
        }
    }
}