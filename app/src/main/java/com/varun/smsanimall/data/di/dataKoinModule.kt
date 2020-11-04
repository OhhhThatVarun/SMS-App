package com.varun.smsanimall.data.di

import com.varun.smsanimall.data.SmsRepositoryImpl
import com.varun.smsanimall.domain.SmsRepository
import org.koin.dsl.module

val dataKoinModule = module {

    single<SmsRepository> {
        SmsRepositoryImpl(get())
    }
}