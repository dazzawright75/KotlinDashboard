package com.bintonet.android.kotlindashboard.api

/**
 * Created by darren.w.wright on 17/01/2018.
 */
import com.bintonet.android.kotlindashboard.model.Dashboard
import retrofit2.Call
import retrofit2.http.GET

interface  DashboardService {
    @GET("/prod/mockcredit/values")
    fun getDashboardValues(): Call<Dashboard>
}