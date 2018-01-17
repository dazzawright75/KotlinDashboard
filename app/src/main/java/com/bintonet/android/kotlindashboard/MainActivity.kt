package com.bintonet.android.kotlindashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import com.bintonet.android.kotlindashboard.customviews.ProgressCircle
import com.bintonet.android.kotlindashboard.model.Dashboard

/**
 * Displays the Main screen.
 */

class MainActivity : AppCompatActivity(), MainContract.MvpView {
    override fun onFetchDataSuccess(dashboard: Dashboard?) {
        val score = dashboard?.creditReportInfo?.score!!

        //Although not used, other values can be taken from the credit report here such as maxScore
        val maxScore = dashboard?.creditReportInfo?.maxScoreValue!!

        scoreTextView!!.text = score.toString()
        updateProgressCircle(score)
    }

    override fun onFetchDataStarted() {

    }

    override fun onfetchDataCompleted() {

    }


    override fun onfetchDataError(t: Throwable) {

    }


    fun updateProgressCircle(score: Int) {
        val progress = score.toFloat() / 700
        progressCircle?.setProgress(progress)
        progressCircle?.startAnimation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        progressCircle = findViewById(R.id.progress_circle) as ProgressCircle
        scoreTextView = findViewById(R.id.tv_score) as TextView


    }

    override fun onResume() {
        super.onResume()
        mainPresenter?.loadData()
    }

    private var mainPresenter: MainPresenter? = null

    internal var scoreTextView: TextView? = null
    internal var progressCircle: ProgressCircle? = null
}



