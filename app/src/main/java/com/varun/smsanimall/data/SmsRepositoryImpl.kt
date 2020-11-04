package com.varun.smsanimall.data

import android.content.ContentResolver
import com.varun.smsanimall.domain.SmsRepository
import com.varun.smsanimall.domain.model.Sms

class SmsRepositoryImpl(private val contentResolver: ContentResolver) : SmsRepository {

    override fun getSmses(): List<Sms> {
        return listOf(Sms("Varun", "Varnu 1", "This is a test message", ""))
    }
}