package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.Dashboard
import com.bintonet.android.kotlindashboard.remote.MockedValuesService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Handles actions from the View and updates the UI as needed.
 */
class MainPresenter internal constructor(private val mView: MainContract.MvpView) : MainContract.Presenter {
    private val mockedValuesService: MockedValuesService

    init {
        mockedValuesService = MockedValuesService()
    }

    override fun loadData() {

        mView.onFetchDataStarted()
        mockedValuesService
                .api
                .creditReportInfo
                .enqueue(object : Callback<Dashboard> {
                    override fun onResponse(call: Call<Dashboard>, response: Response<Dashboard>) {
                        if (response.isSuccessful) {
                            val dashboard = response.body()
                            if (dashboard != null) {
                                mView.onFetchDataSuccess(dashboard)
                            }

                        } else {
                            mView.onfetchDataCompleted()
                        }
                    }

                    override fun onFailure(call: Call<Dashboard>, t: Throwable) {
                        mView.onfetchDataError(t)
                    }
                })

    }



}
