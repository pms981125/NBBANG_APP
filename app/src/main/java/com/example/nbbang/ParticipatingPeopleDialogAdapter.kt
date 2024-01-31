package com.example.nbbang

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.ParticipatingPeopleLayoutBinding

data class ParticipatingPeople(val name: String)

class ParticipatingPeopleDialogAdapter(private val context: Context): RecyclerView.Adapter<ParticipatingPeopleDialogAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ParticipatingPeopleLayoutBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setContents(position: Int) {
            with(items[position]) {
                binding.textViewParticipatingPeople.text = name
            }
        }
    }

    private val itemListData = MutableLiveData<MutableList<ParticipatingPeople>>()
    val items: MutableList<ParticipatingPeople> = mutableListOf()

    fun addUser(user: ParticipatingPeople) {
        items.add(user)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        //itemsListData.value = items
        notifyItemRemoved(position) //변경사항 적용
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ParticipatingPeopleLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContents(position)
    }
}