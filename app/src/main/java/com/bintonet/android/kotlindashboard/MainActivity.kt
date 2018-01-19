package com.bintonet.android.kotlindashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import com.bintonet.android.kotlindashboard.customviews.ProgressCircle
import com.bintonet.android.kotlindashboard.model.Dashboard

/**
 * MainActivity and activity_main.xml form the "view" in my mvp architecture
 * It is responsible for displaying the main view for the application
 * and updating and setting the views values
 */

class MainActivity : AppCompatActivity(), MainContract.MvpView {
    override fun onFetchDataSuccess(dashboard: Dashboard?) {

        // get the credit score value from the credit report
        // and set the text to this
        val score = dashboard?.creditReportInfo?.score!!
        setScoreTextValue(score)
        // and then set the progress circle value aswell
        setProgressCircleValue(score)

        //Although not used, other values can be taken from the credit report here such as maxScore
        // and be used to set other views
        val maxScore = dashboard?.creditReportInfo?.maxScoreValue!!


    }

    // These functions are part of the contract and are c alled from the presenter
    // I am doing nothing with these though for this test so they are blank
    override fun onFetchDataStarted() {}

    override fun onfetchDataCompleted() {}


    override fun onfetchDataError(t: Throwable) {}

    fun setScoreTextValue(score: Int?){
        scoreTextView!!.text = score.toString()
    }


    fun setProgressCircleValue(score: Int) {
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
        when (mainPresenter?.getReportsCountFromLocalDb(this)) {

            0 ->{
                Log.i("MainActivity", "No data found")
                mainPresenter?.fetchDataFromApi(this)
            }
            else -> {
                Log.i("MainActivity", "We have local data already")
                val report = mainPresenter?.getALlReportsLocalDb(this)?.get(0)
                Log.i("MainActivity", report.toString())
                setScoreTextValue(report?.score)
                if (report != null) {
                    val score = report.score
                    if (score != null) {
                        setProgressCircleValue(score)
                    }
                }
                mainPresenter?.fetchDataFromApi(this)
            }
        }
    }

    private var mainPresenter: MainPresenter? = null

    private var scoreTextView: TextView? = null
    private var progressCircle: ProgressCircle? = null
}



