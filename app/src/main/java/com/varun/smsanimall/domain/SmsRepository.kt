package com.varun.smsanimall.domain

import com.varun.smsanimall.domain.model.Sms

interface SmsRepository {
    fun getSmses() : List<Sms>
}