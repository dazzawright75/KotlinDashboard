package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.api.DashboardApi
import com.bintonet.android.kotlindashboard.api.DashboardRestApi
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Standard tests for the MainPresenter Class
 */

class MainPresenterTest{

    lateinit var mockStartMvpView: MainContract.MvpView

    lateinit var mockApi: DashboardApi

    private lateinit var mockMainPresenter: MainPresenter


    @Before
    fun setup() {
        mockStartMvpView = mock<MainContract.MvpView>()

        mockMainPresenter = MainPresenter(mockStartMvpView)

        mockApi = DashboardRestApi()

    }

    @Test
    fun testsWork() {
        Assert.assertTrue(true)
    }


}