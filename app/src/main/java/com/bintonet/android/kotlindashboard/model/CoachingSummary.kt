package com.bintonet.android.kotlindashboard.model

/**
 * Created by darren.w.wright on 15/01/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoachingSummary {

    @SerializedName("activeTodo")
    @Expose
    var activeTodo: Boolean? = null
    @SerializedName("activeChat")
    @Expose
    var activeChat: Boolean? = null
    @SerializedName("numberOfTodoItems")
    @Expose
    var numberOfTodoItems: Int? = null
    @SerializedName("numberOfCompletedTodoItems")
    @Expose
    var numberOfCompletedTodoItems: Int? = null
    @SerializedName("selected")
    @Expose
    var selected: Boolean? = null

}

