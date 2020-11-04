package com.varun.smsanimall.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.varun.smsanimall.R
import com.varun.smsanimall.databinding.ItemSmsBinding
import com.varun.smsanimall.databinding.ItemSmsTimeDividerBinding
import com.varun.smsanimall.domain.model.Sms

class SmsListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val smses = mutableListOf<Sms>()

    fun setSms(sms: List<Sms>) {
        this.smses.apply {
            clear()
            addAll(sms)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == R.layout.item_sms) {
            SmsListViewHolder(DataBindingUtil.inflate(layoutInflater, viewType, parent, false))
        } else {
            SmsListDateViewHolder(DataBindingUtil.inflate(layoutInflater, viewType, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (smses[position].timeElapsed == null) {
            (holder as? SmsListViewHolder)?.bind(smses[position])
        } else {
            (holder as? SmsListDateViewHolder)?.bind(smses[position])
        }
    }

    override fun getItemCount(): Int = smses.size

    override fun getItemViewType(position: Int): Int {
        if (smses[position].timeElapsed == null) {
            return R.layout.item_sms
        }
        return R.layout.item_sms_time_divider
    }

    fun findNewMessagePosition(body: String): Int {
        for ((i, sms) in smses.withIndex()) {
            if (sms.body == body) {
                return i
            }
        }
        return -1
    }

    inner class SmsListViewHolder(val smsItemSmsBinding: ItemSmsBinding) : RecyclerView.ViewHolder(smsItemSmsBinding.root) {

        fun bind(sms: Sms) {
            smsItemSmsBinding.apply {
                this.sms = sms
                executePendingBindings()
            }
        }
    }

    inner class SmsListDateViewHolder(private val smsItemSmsTimeDividerBinding: ItemSmsTimeDividerBinding) : RecyclerView.ViewHolder(smsItemSmsTimeDividerBinding.root) {

        fun bind(sms: Sms) {
            smsItemSmsTimeDividerBinding.apply {
                this.sms = sms
                executePendingBindings()
            }
        }
    }
}