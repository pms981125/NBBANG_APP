package com.example.nbbang

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbbang.databinding.NbbangLayoutBinding

data class NBBANGItem(val period: String, val money: String, val explanation: String)

class NBBANGAdapter(private val context: Context):RecyclerView.Adapter<NBBANGAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NbbangLayoutBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setContents(pos: Int) {
            with(items[pos]) {
                binding.textViewContent.text = period + " " + money + "원"
            }

            binding.buttonInfo.setOnClickListener {
                //권한 확인 추가
                val dialog = NBBANGInfoDialog(context)
                dialog.myDig(items[pos].explanation)
            }

            binding.buttonManage.setOnClickListener {
                //권한 확인 추가
                val intent = Intent(context, NBBANGManagementActivity::class.java)
                intent.putExtra("roomId", 1)
                intent.putExtra("nbbangId", pos)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NbbangLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context)
    }

    private val itemsListData = MutableLiveData<MutableList<NBBANGItem>>()
    val items: MutableList<NBBANGItem> = mutableListOf()

    fun addItem(item: NBBANGItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItem(pos : Int){
        items.removeAt(pos)
        //itemsListData.value = items
        notifyItemRemoved(pos)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContents(position)
    }
}