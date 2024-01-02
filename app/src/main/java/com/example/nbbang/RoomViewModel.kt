package com.example.nbbang

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class Item1(val title: String, val contents: String)
class RoomViewModel: ViewModel() {
    val itemsListData = MutableLiveData<ArrayList<Item1>>()
    val items = ArrayList<Item1>()
    //val ip

    val itemClickEvent = MutableLiveData<Int>()
    var itemLongClick = -1

    init {
        updateList()
    }

    private fun addItem(item: Item1){
        items.add(item)
        itemsListData.value = items
    }

    fun updateItem(pos : Int, item : Item1){
        items[pos] = item
        itemsListData.value = items
    }

    fun deleteItem(pos : Int){
        items.removeAt(pos)
        itemsListData.value = items
    }

    private fun updateList() {
        val userId = 1

        for (i in 1..3) {
            addItem(Item1(i.toString(), i.toString()))
        }
    }
}