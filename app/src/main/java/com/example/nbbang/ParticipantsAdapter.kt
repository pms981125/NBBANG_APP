package com.example.nbbang

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.ParticipantsLayoutBinding

data class Participants(val name: String)

class ParticipantsAdapter(private val context: Context, private val isParticipate: Boolean): RecyclerView.Adapter<ParticipantsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ParticipantsLayoutBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setContents(position: Int) {
            with(items[position]) {
                binding.textViewName.text = name //구현 필요
            }

            if (isParticipate) {
                binding.imageButton.setImageResource(R.drawable.minus)
                binding.imageButton.setOnClickListener {
                    //유저 삭제 구현
                }
            }
            else {
                binding.imageButton.setOnClickListener {
                    //유저 추가 구현
                }
            }
        }
    }

    private val itemListData = MutableLiveData<MutableList<Participants>>()
    val items: MutableList<Participants> = mutableListOf()

    fun addUser(user: Participants) {
        items.add(user)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        itemListData.value = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ParticipantsLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContents(position)
    }
}