package com.varun.smsanimall.domain.repository

import com.varun.smsanimall.domain.model.Sms

interface SmsRepository {
    fun getSmses() : List<Sms>
}