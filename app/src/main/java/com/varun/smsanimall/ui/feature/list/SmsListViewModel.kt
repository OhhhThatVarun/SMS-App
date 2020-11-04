package com.varun.smsanimall.ui.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.varun.smsanimall.domain.SmsRepository
import com.varun.smsanimall.domain.TimeElapsed
import com.varun.smsanimall.domain.model.Sms

class SmsListViewModel(private val smsRepository: SmsRepository) : ViewModel() {

    val smses: LiveData<List<Sms>> get() = _smses
    private val _smses = MutableLiveData<List<Sms>>()

    fun loadSms() {
        //_smses.postValue(smsRepository.getSmses())
        _smses.postValue(formatSmsForList(smsRepository.getSmses()))
    }

    private fun formatSmsForList(smses: List<Sms>): List<Sms> {
        val list = mutableListOf<Sms>()
        var timeElapsed: TimeElapsed? = null
        for (sms in smses) {
            if (sms.timeElapsed != timeElapsed) {
                timeElapsed = sms.timeElapsed!!
                list.add(Sms("", "", "", timeElapsed))
            }
            list.add(sms.copy(timeElapsed = null))
        }
        return list
    }
}