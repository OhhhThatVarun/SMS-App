package com.varun.smsanimall.domain.model

import com.varun.smsanimall.domain.TimeElapsed

data class Sms(
        val sender: String,
        val recipient: String,
        val body: String,
        val timeElapsed: TimeElapsed?
)