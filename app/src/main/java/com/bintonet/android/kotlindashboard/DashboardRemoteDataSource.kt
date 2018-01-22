package com.bintonet.android.kotlindashboard

/**
 * Created by darren.w.wright on 21/01/2018.
 */
import com.bintonet.android.kotlindashboard.model.Dashboard

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Class to setup the Retrofit object
 *
 */

class DashboardRemoteDataSource : DashboardDataSource {

    private val api: DashboardDataSource

    override val dashboardValues: Observable<Dashboard>
        get() = this.api.dashboardValues

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.api = retrofit.create(DashboardDataSource::class.java)
    }

    companion object {
        private val BASE_URL = "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/"
    }
}