package com.bintonet.android.kotlindashboard.model.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Helper class for the SQLite DB.
 */
class ReportDbHelper(context: Context) : SQLiteOpenHelper(context, ReportDbHelper.DATABASE_NAME, null, ReportDbHelper.DATABASE_VERSION)  {

    companion object {
        val KEY_SCORE = "score"
        val KEY_CLIENT_REF = "clientRef"
        val DATABASE_TABLE = "report_table"
        var RESULT_COLUMNS = arrayOf(KEY_CLIENT_REF, KEY_SCORE)

        private val LOG_TAG = ReportDbHelper::class.java.simpleName

        private val DATABASE_NAME = "reportDB.sqlite"
        private val DATABASE_VERSION = 10

        private val DATABASE_CREATE =
                "CREATE TABLE " + DATABASE_TABLE + "(" +  KEY_SCORE + " INTEGER," +
                        KEY_CLIENT_REF + " NOT NULL UNIQUE ON CONFLICT REPLACE" + ")"

    }

    fun clearDbAndRecreate() {
        clearDb()
        onCreate(writableDatabase)
    }

    fun clearDb() {
        writableDatabase.execSQL("DROP TABLE IF EXISTS DATABASE_TABLE")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE)
        onCreate(db)
    }

    /**
     * CREATE
     */

    fun insertReport(report: Report) {
        val values = ContentValues()
        values.put(KEY_SCORE, report.score) // Credit score
        values.put(KEY_CLIENT_REF, report.clientRef)
        writableDatabase.insert(ReportDbHelper.DATABASE_TABLE, null, values)
    }

    /**
     * READ
     */

    fun getAllReport(): ArrayList<Report>? {
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

        if(reportList.size > 0) {
            return reportList
        }else{
            return null
        }

    }

    /**
     * UPDATE
     */

    // Updating single report
    fun updateReport(report: Report): Int {
        val values = ContentValues()
        values.put(KEY_SCORE, report.score)
        values.put(KEY_CLIENT_REF, report.clientRef)
        // updating row
        return writableDatabase.update(ReportDbHelper.DATABASE_TABLE,
                values, KEY_CLIENT_REF + " = ?",
                arrayOf(report.clientRef))
    }

    /**
     * DELETE - none implemented for this project
     */




}