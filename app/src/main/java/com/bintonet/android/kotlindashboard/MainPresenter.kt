package com.bintonet.android.kotlindashboard

import android.content.Context
import android.util.Log
import com.bintonet.android.kotlindashboard.api.DashboardApi
import com.bintonet.android.kotlindashboard.api.DashboardRestApi
import com.bintonet.android.kotlindashboard.model.Dashboard
import com.bintonet.android.kotlindashboard.model.local.ReportDbHelper
import com.bintonet.android.kotlindashboard.model.local.Report

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MainPresenter is the 'presenter' portion of my MVP architecture
 * Handles actions from the View and updates the UI as needed.
 */
class MainPresenter internal constructor(private val mView: MainContract.MvpView?) : MainContract.Presenter {
    private val api: DashboardApi = DashboardRestApi()

    lateinit var reportDbHelper: ReportDbHelper

    override fun addToLocalDb(report : Report, context: Context){
        reportDbHelper = ReportDbHelper(context)
        reportDbHelper.insertReport(report)
    }

    override fun getALlReportsLocalDb(context: Context): List<Report>? {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.getAllReport()
    }

    override fun getReportsCountFromLocalDb(context: Context): Int {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.getAllReport().size
    }

    override fun updateReportLocalDb(context: Context, report: Report): Int {
        reportDbHelper = ReportDbHelper(context)
        return reportDbHelper.updateReport(report)
    }

    // this is the main call for fetching the json from the api and converting into model objects
    // I use Retrofit for api calls as it (for me) is an easier and quicker method for making asyndc api calls
    override fun fetchDataFromApi(context: Context) {

        // notify the mainview that we have started retrieving data
        // maybe show a spinner to the user
        mView?.onFetchDataStarted()
        api.getDashboardValues()
                .enqueue(object : Callback<Dashboard> {
                    override fun onResponse(call: Call<Dashboard>, response: Response<Dashboard>) {
                        //at this point we have a response from the api, so we notify the mainview
                        //can stop showing any spinners for example
                        mView?.onfetchDataCompleted()
                        if (response.isSuccessful) {
                            //the response is reporting it was a success, so we can go ahead and get the data
                            val dashboard = response.body()
                            if (dashboard != null) {
                                // and let the main view know the operation was a success and send it some data
                                // in this case our Dashboard object
                                // Also from here it could be stored in sqlite
                                mView?.onFetchDataSuccess(dashboard)
                                val score = dashboard.creditReportInfo?.score
                                val clientRef = dashboard.creditReportInfo?.clientRef
                                val report = Report(score, clientRef)
                                Log.i("Presenter", report.toString())
                                updateReportLocalDb(context, report)
                            }

                        } else {
                            //the response wasn't successful, so we can notify the main view anyway
                            //so any spinners etc can be stopped
                            //would also normally send back the response message maybe, or at least log it
                            mView?.onfetchDataCompleted()
                        }
                    }

                    override fun onFailure(call: Call<Dashboard>, t: Throwable) {
                        //the response failed completely and we have an exception
                        // that we can update the main view with
                        //maybe display a toast
                        //and stop and spinners
                        mView?.onfetchDataError(t)
                    }
                })
    }
}
