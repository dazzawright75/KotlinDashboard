package com.bintonet.android.kotlindashboard

/**
 * Created by darren.w.wright on 21/01/2018.
 */
import com.bintonet.android.kotlindashboard.model.Dashboard

import retrofit2.http.GET
import rx.Observable

/**
 * Created by darren.w.wright on 16/01/2018.
 */

interface DashboardDataSource {

    @get:GET("prod/mockcredit/values")
    val dashboardValues: Observable<Dashboard>

}