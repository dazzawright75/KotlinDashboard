package com.bintonet.android.kotlindashboard.model

/**
 * Created by darren.w.wright on 15/01/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreditReportInfo {

    @SerializedName("score")
    @Expose
    var score: Int? = null
    @SerializedName("scoreBand")
    @Expose
    var scoreBand: Int? = null
    @SerializedName("clientRef")
    @Expose
    var clientRef: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("maxScoreValue")
    @Expose
    var maxScoreValue: Int? = null
    @SerializedName("minScoreValue")
    @Expose
    var minScoreValue: Int? = null
    @SerializedName("monthsSinceLastDefaulted")
    @Expose
    var monthsSinceLastDefaulted: Int? = null
    @SerializedName("hasEverDefaulted")
    @Expose
    var hasEverDefaulted: Boolean? = null
    @SerializedName("monthsSinceLastDelinquent")
    @Expose
    var monthsSinceLastDelinquent: Int? = null
    @SerializedName("hasEverBeenDelinquent")
    @Expose
    var hasEverBeenDelinquent: Boolean? = null
    @SerializedName("percentageCreditUsed")
    @Expose
    var percentageCreditUsed: Int? = null
    @SerializedName("percentageCreditUsedDirectionFlag")
    @Expose
    var percentageCreditUsedDirectionFlag: Int? = null
    @SerializedName("changedScore")
    @Expose
    var changedScore: Int? = null
    @SerializedName("currentShortTermDebt")
    @Expose
    var currentShortTermDebt: Int? = null
    @SerializedName("currentShortTermNonPromotionalDebt")
    @Expose
    var currentShortTermNonPromotionalDebt: Int? = null
    @SerializedName("currentShortTermCreditLimit")
    @Expose
    var currentShortTermCreditLimit: Int? = null
    @SerializedName("currentShortTermCreditUtilisation")
    @Expose
    var currentShortTermCreditUtilisation: Int? = null
    @SerializedName("changeInShortTermDebt")
    @Expose
    var changeInShortTermDebt: Int? = null
    @SerializedName("currentLongTermDebt")
    @Expose
    var currentLongTermDebt: Int? = null
    @SerializedName("currentLongTermNonPromotionalDebt")
    @Expose
    var currentLongTermNonPromotionalDebt: Int? = null
    @SerializedName("currentLongTermCreditLimit")
    @Expose
    var currentLongTermCreditLimit: Any? = null
    @SerializedName("currentLongTermCreditUtilisation")
    @Expose
    var currentLongTermCreditUtilisation: Any? = null
    @SerializedName("changeInLongTermDebt")
    @Expose
    var changeInLongTermDebt: Int? = null
    @SerializedName("numPositiveScoreFactors")
    @Expose
    var numPositiveScoreFactors: Int? = null
    @SerializedName("numNegativeScoreFactors")
    @Expose
    var numNegativeScoreFactors: Int? = null
    @SerializedName("equifaxScoreBand")
    @Expose
    var equifaxScoreBand: Int? = null
    @SerializedName("equifaxScoreBandDescription")
    @Expose
    var equifaxScoreBandDescription: String? = null
    @SerializedName("daysUntilNextReport")
    @Expose
    var daysUntilNextReport: Int? = null

}

