package com.varun.smsanimall.ui.di

import com.varun.smsanimall.ui.feature.list.SmsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiKoinModule = module {

    viewModel {
        SmsListViewModel(get())
    }
}