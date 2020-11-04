package com.varun.smsanimall.ui.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        viewModel.smses.observe(viewLifecycleOwner, {
            binding.adapter?.setSms(it)
        })
    }
}