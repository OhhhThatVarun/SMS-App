package com.varun.smsanimall.ui.ext

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <T : ViewDataBinding> Fragment.getDataBindings(layout: Int, container: ViewGroup?): T {
    val bindings: T = DataBindingUtil.inflate(layoutInflater, layout, container, false)
    bindings.lifecycleOwner = viewLifecycleOwner
    return bindings
}