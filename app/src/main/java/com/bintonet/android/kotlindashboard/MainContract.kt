package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.Dashboard

/**
 * Created by darren.w.wright on 17/01/2018.
 */
interface MainContract{
    interface MvpView{

        fun onFetchDataStarted()

        fun onfetchDataCompleted()

        fun onFetchDataSuccess(dashboard: Dashboard?)

        fun onfetchDataError(t: Throwable)
    }
    interface Presenter{
        fun loadData()
    }

}