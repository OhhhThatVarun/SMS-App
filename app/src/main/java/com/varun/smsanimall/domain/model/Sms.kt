package com.varun.smsanimall.domain.model

import com.varun.smsanimall.domain.TimeElapsed

/*
* Sms representation in the domain layer.
* */
data class Sms(
        val sender: String,
        val recipient: String,
        val body: String,
        val timeElapsed: TimeElapsed?
)