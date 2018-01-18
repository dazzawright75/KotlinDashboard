package com.bintonet.android.kotlindashboard.api

/**
 * Service class
 * defines the URL parameters and the Call method
 */
import com.bintonet.android.kotlindashboard.model.Dashboard
import retrofit2.Call
import retrofit2.http.GET

interface  DashboardService {
    @GET("/prod/mockcredit/values")
    fun getDashboardValues(): Call<Dashboard>

    // other calls can be defined here for other api's at the same endpoint

}