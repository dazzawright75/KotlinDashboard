package com.bintonet.android.kotlindashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.bintonet.android.kotlindashboard.customviews.ProgressCircle
import com.bintonet.android.kotlindashboard.model.local.Report

/**
 * MainActivity and activity_main.xml form the "view" in my mvp architecture
 * It is responsible for displaying the main view for the application
 * and updating and setting the views values
 */

class MainActivity : AppCompatActivity(), MainContract.MvpView {

    lateinit var mainPresenter: MainPresenter

    lateinit var scoreTextView: TextView

    lateinit var progressCircle: ProgressCircle

    override fun onFetchDataSuccess(report: Report?) {

        // get the credit score value from the credit report
        // and set the text to this

        val score = report?.score

        // and then set the progress circle value aswell
        if (score != null) {
            setScoreTextValue(score)
            setProgressCircleValue(score)
        }

        //Although not used, other values can be taken from the credit report here such as maxScore
        // and be used to set other views


    }

    // These functions are part of the contract and are c alled from the presenter
    // I am doing nothing with these though for this test so they are blank
    override fun onFetchDataStarted() {}

    override fun onfetchDataCompleted() {}

    override fun onfetchDataError(t: Throwable) {}

    fun setScoreTextValue(score: Int?){
        scoreTextView.text = score.toString()
    }

    fun setProgressCircleValue(score: Int) {
        val progress = score.toFloat() / 700
        progressCircle.setProgress(progress)
        progressCircle.startAnimation()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        mainPresenter = MainPresenter(this)
        progressCircle = findViewById(R.id.progress_circle) as ProgressCircle
        scoreTextView = findViewById(R.id.tv_score) as TextView
    }

    override fun onResume() {
        super.onResume()
        // in on resume we should check to see if the app has some data loaded
        // if it does then display that first and then call the remote API
        // if it doesn't then we shoujld just call the remote API

        when (mainPresenter?.getReportsCountFromLocalDb(this)) {

            0 ->{
                // we have no local data yet
                mainPresenter?.fetchDataFromApi(this)
            }
            else -> {

                // we have some local data so we will use that
                mainPresenter?.fetchDataFromLocalDb(this)

                // and then update what we already have
                // depending on the app then this may only need to be called at
                // set times/frequency rather than every time
                mainPresenter?.fetchDataFromApi(this)
            }
        }
    }

}



