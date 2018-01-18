package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.Dashboard

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
        fun fetchData()
    }

}