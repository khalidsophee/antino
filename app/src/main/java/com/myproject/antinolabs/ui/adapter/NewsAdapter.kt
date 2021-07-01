package com.myproject.antinolabs.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myproject.antinolabs.R
import com.myproject.antinolabs.data.Model
import com.myproject.antinolabs.databinding.NotificationViewBinding
import com.myproject.antinolabs.utils.OnItemClickListener

// Created by Khalid on 1/07/2020.

class NewsAdapter(
    private val items: List<Model>,
    val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val notificationBinding =
            NotificationViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(notificationBinding, parent)
    }

    override fun getItemCount() = items.size

    class ViewHolder(val view: NotificationViewBinding, val parent: ViewGroup) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(notification: Model, listener: View.OnClickListener) {
            view.root.setOnClickListener(listener)
            view.tvTitle.text = notification.source?.name
            view.tvTime.text = notification.author
            view.imageView.load(notification.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.ic_india)
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(items[position]) { itemClickListener.onItemClick(position) }
    }

}