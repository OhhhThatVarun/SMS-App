package com.varun.smsanimall.platform

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import androidx.navigation.NavDeepLinkBuilder
import com.varun.smsanimall.R
import com.varun.smsanimall.ui.MainActivity
import com.varun.smsanimall.ui.feature.list.SmsListFragment.Companion.TIMESTAMP_MILLIS_KEY


class SMSBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("SMSBroadcastReceiver", "Got a message")

        if (intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            val smsMessages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (message in smsMessages) {
                if (context != null) {
                    val pendingIntent = NavDeepLinkBuilder(context)
                            .setComponentName(MainActivity::class.java)
                            .setGraph(R.navigation.nav_graph)
                            .setDestination(R.id.smsListFragment)
                            .setArguments(Bundle().apply {
                                putLong(TIMESTAMP_MILLIS_KEY, message.timestampMillis)
                            }).createPendingIntent()
                    Notifier.postNotification(1, "New Message From ${getSenderName(message)}", message.messageBody, context, pendingIntent)
                }
            }
        }
    }

    private fun getSenderName(smsMessages: SmsMessage): String? {
        return smsMessages.originatingAddress
    }
}