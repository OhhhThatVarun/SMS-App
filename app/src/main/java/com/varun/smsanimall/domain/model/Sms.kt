package com.varun.smsanimall.domain.model

data class Sms(
    val sender: String,
    val recipient: String,
    val message: String,
    val timestamp: String
)