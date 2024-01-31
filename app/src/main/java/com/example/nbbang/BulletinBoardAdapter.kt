package com.example.nbbang

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.BulletinboardLayoutBinding

data class BulletinBoardItem(val username: String, val content:String)

class BulletinBoardAdapter(private val context: Context):RecyclerView.Adapter<BulletinBoardAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: BulletinboardLayoutBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setContents(pos: Int) {
            with(items[pos]) {
                binding.textViewUser.text = username
                binding.textViewContent.text = content
            }

            binding.buttonDelete.setOnClickListener {
                deleteItem(pos)
            }
        }
    }

    private val itemsListData = MutableLiveData<MutableList<BulletinBoardItem>>()
    val items: MutableList<BulletinBoardItem> = mutableListOf()

    fun addItem(item: BulletinBoardItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItem(pos : Int){
        items.removeAt(pos)
        //itemsListData.value = items
        notifyItemRemoved(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BulletinboardLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContents(position)
    }

}