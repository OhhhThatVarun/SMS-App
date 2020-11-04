package com.varun.smsanimall.ui.feature.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.varun.smsanimall.R
import com.varun.smsanimall.databinding.FragmentSmsListBinding
import com.varun.smsanimall.ui.adapter.SmsListRecyclerViewAdapter
import com.varun.smsanimall.ui.ext.getDataBindings
import org.koin.android.viewmodel.ext.android.viewModel


class SmsListFragment : Fragment() {

    private lateinit var binding: FragmentSmsListBinding
    private val viewModel: SmsListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getDataBindings(R.layout.fragment_sms_list, container)
        return binding.apply {
            adapter = SmsListRecyclerViewAdapter()
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestSmsPermission()
        viewModel.smses.observe(viewLifecycleOwner, {
            binding.adapter?.setSms(it)
        })
    }

    private fun requestSmsPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_SMS), SMS_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == SMS_PERMISSION_CODE) {
            viewModel.loadSms()
        } else {
            Snackbar.make(binding.root, "Need SMS permission to show mesages", Snackbar.LENGTH_LONG).setAction("Ask Again") {
                requestSmsPermission()
            }.show()
        }
    }

    companion object {
        private const val SMS_PERMISSION_CODE = 0
    }
}