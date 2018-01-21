package com.bintonet.android.kotlindashboard

import android.content.Context
import android.util.Log
import com.bintonet.android.kotlindashboard.model.Dashboard
import com.bintonet.android.kotlindashboard.model.local.ReportDbHelper
import com.bintonet.android.kotlindashboard.model.local.Report

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.Scheduler


/**
 * MainPresenter is the 'presenter' portion of my MVP architecture
 * Handles actions from the View and updates the UI as needed.
 */

class MainPresenter internal constructor(private var mView: MainContract.MvpView?,
                                         var dashboardDataSource: DashboardDataSource?,
                                         private var backgroundScheduler: Scheduler?,
                                         private var mainScheduler: Scheduler?

                                         ) : MainContract.Presenter {

    val LOG_TAG = MainPresenter::class.java.simpleName

    lateinit var reportDbHelper: ReportDbHelper

    override fun addToLocalDb(report: Report, context: Context) {
        reportDbHelper = ReportDbHelper(context)
        reportDbHelper.insertReport(report)
    }

    override fun getALlReportsLocalDb(context: Context): List<Report>? {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.getAllReport()
    }

    override fun getReportsCountFromLocalDb(context: Context): Int? {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.getAllReport()?.size
    }

    override fun updateReportLocalDb(context: Context, report: Report): Int {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.updateReport(report)
    }

    // this is the main call for fetching the json from the api and converting into model objects
    // I use Retrofit for api calls as it (for me) is an easier and quicker method for making asyndc api calls
    override fun fetchDataFromApi(context: Context, isNew: Boolean) {

        mView?.onFetchDataStarted()

        DashboardRemoteDataSource().dashboardValues
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Dashboard>() {
                    override fun onCompleted() {
                        Log.i(LOG_TAG, " >> onCompleted")
                        mView?.onfetchDataCompleted()
                    }

                    override fun onError(e: Throwable) {
                        Log.i(LOG_TAG, " >> onError")
                        mView?.onfetchDataError(e)
                    }

                    override fun onNext(dashboard: Dashboard) {
                        val score = dashboard.creditReportInfo?.score
                        val clientRef = dashboard.creditReportInfo?.clientRef
                        val report = Report(score, clientRef)
                        if (isNew) {
                            addToLocalDb(report, context)
                        } else {
                            updateReportLocalDb(context, report)
                        }

                        mView?.onFetchDataSuccess(report)
                    }
                })
    }

    override fun fetchData(context: Context) {
        Log.i(LOG_TAG, "fetchDataFromLocalDb")
        val reportsList = getALlReportsLocalDb(context)
        if (reportsList != null) {
            Log.i(LOG_TAG, "We have some local data")
            mView?.onFetchDataSuccess(reportsList[0])
            fetchDataFromApi(context, false)
        } else {
            Log.i(LOG_TAG, "Nothing in the local DB")
            fetchDataFromApi(context, true)
        }
    }

}
