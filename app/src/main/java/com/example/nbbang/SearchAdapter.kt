package com.example.nbbang

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.RoomLayoutBinding

class SearchAdapter(private val viewModel: SearchViewModel, private val activity: Activity, private val context: Context): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: RoomLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setContents(pos: Int) {
            with(viewModel.items[pos]) {
                binding.titleTextView.text = title
                binding.contentsTextView.text = contents
            }

            binding.root.setOnClickListener {
                viewModel.itemClickEvent.value = pos
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RoomLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.setContents(position)
    }

    override fun getItemCount(): Int {
        return viewModel.items.size
    }
}