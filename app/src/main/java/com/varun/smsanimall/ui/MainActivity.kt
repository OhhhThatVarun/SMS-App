package com.varun.smsanimall.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.varun.smsanimall.R
import com.varun.smsanimall.databinding.ActivityMainBinding
import com.varun.smsanimall.platform.Notifier

class MainActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Notifier.init(this)
    }
}