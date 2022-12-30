package com.gulehri.whatsappreader.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gulehri.whatsappreader.R
import com.gulehri.whatsappreader.data.models.SingleNotification
import com.gulehri.whatsappreader.databinding.SingleNotificationItemBinding
import com.gulehri.whatsappreader.utils.Extensions.hide


// Created by Shahid Iqbal on 12/26/2022.

class SaveShowAdapter :
    ListAdapter<SingleNotification, SaveShowAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleNotificationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) holder.bindTo(currentItem)
    }

    inner class ViewHolder(private val binding: SingleNotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTo(currentItem: SingleNotification) {
            binding.apply {

                tvTitle.text = currentItem.title
                tvMessage.text = currentItem.text
                tvDateTime.text = currentItem.postTime
                smallIcon.setImageBitmap(currentItem.appIcon)

                currentItem.profileImage?.let {
                    profileImage.setImageBitmap(it)
                } ?: profileImage.setImageResource(R.drawable.profile)

                tvMessageDetails.hide()

                if (currentItem.text!!.contains("new", true)) {

                    tvMessage.text = "${currentItem.text} (click to see)"
                    root.setOnClickListener {
                        MaterialAlertDialogBuilder(root.context)
                            .setTitle("Stack Messages")
                            .setMessage(currentItem.stackMsg)
                            .setPositiveButton("ok", null)
                            .show()
                    }
                }


            }
        }


    }

    class DiffCallback : DiffUtil.ItemCallback<SingleNotification>() {
        override fun areItemsTheSame(oldItem: SingleNotification, newItem: SingleNotification) =
            oldItem.postTime == newItem.postTime

        override fun areContentsTheSame(oldItem: SingleNotification, newItem: SingleNotification) =
            oldItem == newItem
    }
}