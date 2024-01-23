package com.example.nbbang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.NbbangLayoutBinding

data class NBBANGItem(val period: String, val money: String, val explanation: String)

class NBBANGAdapter:RecyclerView.Adapter<NBBANGAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NbbangLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setContents(pos: Int) {
            with(items[pos]) {
                binding.textViewContent.text = period + " " + money + "원"
            }

            binding.buttonInfo.setOnClickListener {
                //추가
            }

            binding.buttonManage.setOnClickListener {
                //추가
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NbbangLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    private val items: MutableList<NBBANGItem> = mutableListOf()

    fun addItem(item: NBBANGItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContents(position)
    }
}