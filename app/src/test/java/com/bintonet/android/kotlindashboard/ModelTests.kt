package com.bintonet.android.kotlindashboard

import com.bintonet.android.kotlindashboard.model.CreditReportInfo
import com.bintonet.android.kotlindashboard.model.Dashboard
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

/**
 * Standard tests for the model classes
 */

class ModelTests{

    //
    //ran into kotlin problem here with all classes being final, they wont let me mock them :(
    //

//    @Test
//    fun test_getting_credit_report_info() {
//        val creditReportInfo = Mockito.mock(CreditReportInfo::class.java)
//
//        Mockito.`when`(creditReportInfo.score).thenReturn(creditReportInfo.score)
//        assertNotNull(creditReportInfo.score)
//    }
}