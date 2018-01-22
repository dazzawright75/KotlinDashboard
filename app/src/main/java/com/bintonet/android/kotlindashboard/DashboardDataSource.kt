package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.Dashboard

import retrofit2.http.GET
import rx.Observable

/**
 * Class to specify the REST calls for Retrofit
 * All calls to the same endpoint would be added here
 */

interface DashboardDataSource {

    @get:GET("prod/mockcredit/values")
    val dashboardValues: Observable<Dashboard>

    // any other REST calls to be added here e.g PUT,POST etc....

}