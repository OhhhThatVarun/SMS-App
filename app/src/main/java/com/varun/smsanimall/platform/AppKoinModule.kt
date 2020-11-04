package com.varun.smsanimall.platform

import android.content.Context
import org.koin.dsl.module

val appKoinModule = module {

    single {
        get<Context>().contentResolver
    }
}