package com.bintonet.android.kotlindashboard

import android.content.Context
import com.bintonet.android.kotlindashboard.model.Dashboard
import com.bintonet.android.kotlindashboard.model.local.Report

/**
 * This is the contract that defines the View & Presenter
 */
interface MainContract{
    interface MvpView{

        fun onFetchDataStarted()

        fun onfetchDataCompleted()

        fun onFetchDataSuccess(dashboard: Dashboard?)

        fun onfetchDataError(t: Throwable)
    }
    interface Presenter{
        fun getReportsCountFromLocalDb(context: Context): Int
        fun getALlReportsLocalDb(context: Context): List<Report>?
        fun addToLocalDb(report: Report, context: Context)
        fun fetchDataFromApi(context: Context)
        fun updateReportLocalDb(context: Context, report: Report): Int
    }

}