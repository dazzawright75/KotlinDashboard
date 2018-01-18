package com.bintonet.android.kotlindashboard.api

/**
 * Dashboard APi
 */
import com.bintonet.android.kotlindashboard.model.Dashboard
import retrofit2.Call

/**
 * DashboardApi
 */
interface DashboardApi {
    fun getDashboardValues(): Call<Dashboard>
}