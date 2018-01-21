package com.bintonet.android.kotlindashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import com.bintonet.android.kotlindashboard.customviews.ProgressCircle
import com.bintonet.android.kotlindashboard.model.local.Report


/**
 * MainActivity and activity_main.xml form the "view" in my mvp architecture
 * It is responsible for displaying the main view for the application
 * and updating and setting the views values
 */

class MainActivity : AppCompatActivity(), MainContract.MvpView {

    val LOG_TAG = MainActivity::class.java.simpleName

    lateinit var mainPresenter: MainPresenter

    lateinit var scoreTextView: TextView

    lateinit var progressCircle: ProgressCircle

    fun setScoreTextValue(score: Int?){
        scoreTextView.text = score.toString()
    }

    fun setProgressCircleValue(score: Int) {
        val progress = score.toFloat() / 700
        progressCircle.setProgress(progress)
        progressCircle.startAnimation()
    }

    override fun onFetchDataSuccess(report: Report?) {
        Log.i(LOG_TAG, "onFetchDataSuccess : " + report.toString())

        // get the credit score value from the credit report
        // and set the text to this

        val score = report?.score

        // and then set the progress circle value
        if (score != null) {
            setScoreTextValue(score)
            setProgressCircleValue(score)
        }

        //Although not used, other values can be taken from the credit report here such as maxScore
        // and be used to set other views

    }

    // These functions are part of the contract and are c alled from the presenter
    // I am doing nothing with these though for this test so they are blank
    override fun onFetchDataStarted() {
        Log.i(LOG_TAG, "onFetchDataStarted")
    }

    override fun onfetchDataCompleted() {
        Log.i(LOG_TAG, "onfetchDataCompleted")
    }

    override fun onfetchDataError(t: Throwable) {
        Log.i(LOG_TAG, "onfetchDataError")
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        mainPresenter = MainPresenter(this, null, null, null)
        progressCircle = findViewById(R.id.progress_circle) as ProgressCircle
        scoreTextView = findViewById(R.id.tv_score) as TextView
    }

    override fun onResume() {
        super.onResume()
        // we can load some data here
        mainPresenter.fetchData(this)

    }

}



