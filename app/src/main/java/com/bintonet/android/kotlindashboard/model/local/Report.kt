package com.bintonet.android.kotlindashboard.model.local

/**
 * Really simple class for the Object to be stored in SQLite
 * Real world the this would use one of the remote object classes, but for simplicity i have
 * created this simpler one.
 */

data class Report(var score: Int?, var clientRef: String?)