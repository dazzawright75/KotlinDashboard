package com.bintonet.android.kotlindashboard
import org.junit.Test
import org.mockito.Mockito
import rx.schedulers.Schedulers
import com.bintonet.android.kotlindashboard.model.Dashboard
import com.bintonet.android.kotlindashboard.model.local.Report
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import rx.Observable
import org.mockito.Mockito.`when`


/**
 * Standard tests for the MainPresenter Class
 */

class MainPresenterTest : BaseTest() {

    @Mock
    private val dashboardDataSource: DashboardDataSource? = null

    @Mock
    private val view: MainContract.MvpView? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchValidDataShouldLoadIntoView() {

        val dashboard = Dashboard()
        val report = Report(123, "ABC-1234")


        `when`(dashboardDataSource!!.dashboardValues)
                .thenReturn(Observable.just<Dashboard>(dashboard))

        val mainPresenter = MainPresenter(
                this.view,
                this.dashboardDataSource,
                Schedulers.immediate(),
                Schedulers.immediate()
        )

        mainPresenter.fetchDataFromApi(context(), false)

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1))?.onFetchDataStarted()

    }

    @Test
    fun fetchErrorShouldReturnErrorToView() {

        val report = Report(123, "ABC-1234")
        val exception = Exception()

        `when`(dashboardDataSource!!.dashboardValues)
                .thenReturn(Observable.error<Dashboard>(exception))

        val mainPresenter = MainPresenter(
                this.view,
                this.dashboardDataSource,
                Schedulers.immediate(),
                Schedulers.immediate())

        mainPresenter.fetchDataFromApi(context(), false)

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1))?.onFetchDataStarted()
        verify(view, never())?.onFetchDataSuccess(report)
    }

}