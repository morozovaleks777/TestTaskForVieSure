package com.morozov.common



import com.morozov.common.utils.toCustomDateFormat
import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterTest {
    @Test
    fun testValidDate() {
        val input = "6/8/1949"
        val expectedOutput = "Wed, June 8, '49"
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }

    @Test
    fun testEmptyString() {
        val input = ""
        val expectedOutput = ""
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }

    @Test
    fun testBlankString() {
        val input = "   "
        val expectedOutput = ""
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }

    @Test
    fun testInvalidDateFormat() {
        val input = "2020-01-01"
        val expectedOutput = ""
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }

    @Test
    fun testNonDateString() {
        val input = "Not a date"
        val expectedOutput = ""
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }

    @Test
    fun testInvalidDate() {
        val input = "13/40/2020"  // Invalid month and day
        val expectedOutput = ""
        assertEquals(expectedOutput, input.toCustomDateFormat())
    }
}
