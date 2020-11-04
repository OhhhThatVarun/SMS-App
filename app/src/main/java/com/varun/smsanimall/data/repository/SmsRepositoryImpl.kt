package com.varun.smsanimall.data.repository

import android.content.ContentResolver
import android.provider.Telephony
import com.varun.smsanimall.data.entity.SmsEntity
import com.varun.smsanimall.domain.repository.SmsRepository
import com.varun.smsanimall.domain.model.Sms

class SmsRepositoryImpl(private val contentResolver: ContentResolver) : SmsRepository {

    override fun getSmses(): List<Sms> {
        val smses = mutableListOf<SmsEntity>()
        val cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
        while (cursor != null && cursor.moveToNext()) {
            val timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
            val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
            val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
            smses.add(SmsEntity(number, "", body, timestamp))
        }
        cursor?.close()
        return smses.map {
            it.transform()
        }
    }
}