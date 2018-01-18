package com.bintonet.android.kotlindashboard.api

/**
 * Retrofit API class
 * Builds the retrofit class
 */
import com.bintonet.android.kotlindashboard.model.Dashboard
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DashboardRestApi() : DashboardApi {

    private val dashboardService: DashboardService
    private val BASE_URL = "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/"

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        dashboardService = retrofit.create(DashboardService::class.java)
    }

    override fun getDashboardValues(): Call<Dashboard> {
        return dashboardService.getDashboardValues()
    }
}