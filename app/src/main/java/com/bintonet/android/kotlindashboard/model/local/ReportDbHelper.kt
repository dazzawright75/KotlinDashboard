package com.bintonet.android.kotlindashboard.model.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by darren.w.wright on 19/01/2018.
 */
class ReportDbHelper(context: Context) : SQLiteOpenHelper(context, ReportDbHelper.DATABASE_NAME, null, ReportDbHelper.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.w(LOG_TAG, "On Create")
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(LOG_TAG, "Upgrade from version $oldVersion to $newVersion")
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE)
        onCreate(db)
    }

    fun clearDbAndRecreate() {
        Log.w(LOG_TAG, "clearDbAndRecreate")
        clearDb()
        onCreate(writableDatabase)
    }

    fun clearDb() {
        Log.w(LOG_TAG, "clearDb")
        writableDatabase.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
    }

    /**
     * READ
     */


    fun getAllReport(): ArrayList<Report> {
        val reportList = arrayListOf<Report>()

        val cursor = writableDatabase.query(ReportDbHelper.DATABASE_TABLE, ReportDbHelper.RESULT_COLUMNS,
                null, null, null, null, ReportDbHelper.KEY_CLIENT_REF)

        while (cursor != null && cursor.moveToNext()) {
            val report = Report(
                    cursor.getInt(1), cursor.getString(0)
            )
            reportList.add(report)
        }

        cursor.close()

        return reportList
    }

    fun insertText(report: Report) {
        val newValue = ContentValues()
        newValue.put(ReportDbHelper.KEY_CLIENT_REF, report.clientRef)
        newValue.put(ReportDbHelper.KEY_SCORE, report.score)
        writableDatabase.insert(ReportDbHelper.DATABASE_TABLE, null, newValue)
    }

    fun insertReport(report: Report) {
        Log.i(LOG_TAG, "insert - " + report.toString())
        val values = ContentValues()
        values.put(KEY_SCORE, report.score) // Credit score
        values.put(KEY_CLIENT_REF, report.clientRef)
        writableDatabase.insert(ReportDbHelper.DATABASE_TABLE, null, values)
    }

    // Updating single blog
    fun updateReport(report: Report): Int {
        val values = ContentValues()
        values.put(KEY_SCORE, report.score)
        values.put(KEY_CLIENT_REF, report.clientRef)
// updating row
        return writableDatabase.update(ReportDbHelper.DATABASE_TABLE,
                values, KEY_CLIENT_REF + " = ?",
                arrayOf(report.clientRef))
    }


//    fun updateReport(report: Report) {
//        Log.i(LOG_TAG, "insert - " + report.toString())
//        val values = ContentValues()
//        values.put(KEY_SCORE, report.score) // Credit score
//        values.put(KEY_CLIENT_REF, report.clientRef)
//        writableDatabase.insert(ReportDbHelper.DATABASE_TABLE, null, values)
//    }

    companion object {
        val KEY_ID = "_ID"
        val KEY_SCORE = "score"
        val KEY_CLIENT_REF = "clientRef"
        val DATABASE_TABLE = "report_table"
        var RESULT_COLUMNS = arrayOf(KEY_CLIENT_REF, KEY_SCORE)

        private val LOG_TAG = ReportDbHelper::class.java.simpleName

        private val DATABASE_NAME = "reportDB.sqlite"
        private val DATABASE_VERSION = 10

//        private val DATABASE_CREATE =
//                "CREATE TABLE $DATABASE_TABLE ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "$KEY_CLIENT_REF TEXT NOT NULL UNIQUE ON CONFLICT REPLACE);"

        private val DATABASE_CREATE =
                "CREATE TABLE " + DATABASE_TABLE + "(" +  KEY_SCORE + " INTEGER," +
                        KEY_CLIENT_REF + " NOT NULL UNIQUE ON CONFLICT REPLACE" + ")"


    }
}