package com.varun.smsanimall.data.di

import com.varun.smsanimall.data.repository.SmsRepositoryImpl
import com.varun.smsanimall.domain.repository.SmsRepository
import org.koin.dsl.module

val dataKoinModule = module {

    single<SmsRepository> {
        SmsRepositoryImpl(get())
    }
}