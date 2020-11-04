package com.varun.smsanimall.data.entity

import com.varun.smsanimall.domain.TimeElapsed
import com.varun.smsanimall.domain.model.Sms

/*
* Sms representation in the data layer
* */
data class SmsEntity(
        val sender: String,
        val recipient: String,
        val body: String,
        val timestamp: Long
) {
    fun transform(): Sms {
        return Sms(sender, recipient, body, getTimeElapsed(timestamp))
    }

    private fun getTimeElapsed(time: Long): TimeElapsed {
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return TimeElapsed.Day_AGO
        }
        val diff = now - time
        return when {
            diff < HOUR_MILLIS -> {
                TimeElapsed.Zero_Hour_Ago
            }
            diff < 2 * HOUR_MILLIS -> {
                TimeElapsed.One_Hour_Ago
            }
            diff < 3 * HOUR_MILLIS -> {
                TimeElapsed.Two_Hours_Ago
            }
            diff < 4 * HOUR_MILLIS -> {
                TimeElapsed.Three_Hours_Ago
            }
            diff < 7 * HOUR_MILLIS -> {
                TimeElapsed.Six_Hours_Ago
            }
            diff < 13 * HOUR_MILLIS -> {
                TimeElapsed.Twelve_Hours
            }
            diff < 25 * HOUR_MILLIS -> {
                TimeElapsed.Day_AGO
            }
            else -> {
                TimeElapsed.Day_AGO
            }
        }
    }

    companion object {
        private const val HOUR_MILLIS = 60 * 60 * 1000
    }
}
