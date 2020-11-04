package com.varun.smsanimall.ui.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.varun.smsanimall.domain.SmsRepository
import com.varun.smsanimall.domain.model.Sms

class SmsListViewModel(private val smsRepository: SmsRepository) : ViewModel() {

    val smses: LiveData<List<Sms>> get() = _smses
    private val _smses = MutableLiveData<List<Sms>>()

    fun loadSms() {
        _smses.postValue(smsRepository.getSmses())
    }
}