package com.varun.smsanimall.domain

enum class TimeElapsed {
    Zero_Hour_Ago, One_Hour_Ago, Two_Hours_Ago, Three_Hours_Ago, Six_Hours_Ago, Twelve_Hours, Day_AGO;

    override fun toString(): String {
        return when (this) {
            Zero_Hour_Ago -> "0 Hour Ago"
            One_Hour_Ago -> "1 Hour Ago"
            Two_Hours_Ago -> "2 Hours Ago"
            Three_Hours_Ago -> "3 Hours Ago"
            Six_Hours_Ago -> "6 Hours Ago"
            Twelve_Hours -> "12 Hours Ago"
            Day_AGO -> "1 Day Ago"
        }
    }
}