package com.varun.smsanimall.domain.model

data class Sms(
        val sender: String,
        val recipient: String,
        val body: String,
        val timestamp: Long
)