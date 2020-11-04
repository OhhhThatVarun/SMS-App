package com.varun.smsanimall.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.varun.smsanimall.R
import com.varun.smsanimall.databinding.ItemSmsBinding
import com.varun.smsanimall.domain.model.Sms

class SmsListRecyclerViewAdapter : RecyclerView.Adapter<SmsListRecyclerViewAdapter.SmsListViewHolder>() {

    private val smses = mutableListOf<Sms>()

    fun setSms(sms: List<Sms>) {
        this.smses.apply {
            clear()
            addAll(sms)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsListViewHolder {
        return SmsListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: SmsListViewHolder, position: Int) {
        holder.bind(smses[position])
    }

    override fun getItemCount(): Int = smses.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_sms
    }

    inner class SmsListViewHolder(private val smsItemSmsBinding: ItemSmsBinding) : RecyclerView.ViewHolder(smsItemSmsBinding.root) {

        fun bind(sms: Sms) {
            smsItemSmsBinding.apply {
                this.sms = sms
                executePendingBindings()
            }
        }
    }
}