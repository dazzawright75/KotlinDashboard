package com.bintonet.android.kotlindashboard.api

/**
 * Created by darren.w.wright on 17/01/2018.
 */
import com.bintonet.android.kotlindashboard.model.Dashboard
import retrofit2.Call

/**
 * News API
 *
 * @author juancho.
 */
interface DashboardApi {
    fun getDashboardValues(): Call<Dashboard>
}