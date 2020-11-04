package com.varun.smsanimall.ui.feature.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
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
            if (arguments != null && arguments?.getLong(MESSAGE_BODY_KEY) != null) {
                Log.e("Got some data", arguments?.getLong(MESSAGE_BODY_KEY)!!.toString())
                val position = binding.adapter?.findNewMessagePosition(arguments?.getString(MESSAGE_BODY_KEY)!!)
                if (position != null) {
                    binding.recyclerview.post {
                        val viewHolder = binding.recyclerview.findViewHolderForLayoutPosition(position) as? SmsListRecyclerViewAdapter.SmsListViewHolder
                        animateRecyclerViewItem(viewHolder?.smsItemSmsBinding?.root)
                    }
                }
            }
        })
    }

    private fun animateRecyclerViewItem(view: View?) {
        view?.startAnimation(AlphaAnimation(0.0f, 1.0f).apply {
            duration = 250
            startOffset = 20
            repeatMode = Animation.REVERSE
            repeatCount = 5
        })
    }

    private fun requestSmsPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS), SMS_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == SMS_PERMISSION_CODE) {
            viewModel.loadSms()
        } else {
            Snackbar.make(binding.root, R.string.need_sms_permission, Snackbar.LENGTH_LONG).setAction(R.string.ask_again) {
                requestSmsPermission()
            }.show()
        }
    }

    companion object {
        const val MESSAGE_BODY_KEY = "MESSAGE_BODY_KEY"
        private const val SMS_PERMISSION_CODE = 0
    }
}