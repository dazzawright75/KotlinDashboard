package com.bintonet.android.kotlindashboard.model

/**
 * Created by darren.w.wright on 15/01/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dashboard {

    @SerializedName("accountIDVStatus")
    @Expose
    var accountIDVStatus: String? = null
    @SerializedName("creditReportInfo")
    @Expose
    var creditReportInfo: CreditReportInfo? = null
    @SerializedName("dashboardStatus")
    @Expose
    var dashboardStatus: String? = null
    @SerializedName("personaType")
    @Expose
    var personaType: String? = null
    @SerializedName("coachingSummary")
    @Expose
    var coachingSummary: CoachingSummary? = null
    @SerializedName("augmentedCreditScore")
    @Expose
    var augmentedCreditScore: Any? = null

}

