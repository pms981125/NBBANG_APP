package com.example.nbbang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbbang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = RoomViewModel()
    private lateinit var adapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.roomRecyclerView
        adapter = RoomAdapter(viewModel, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        viewModel.itemsListData.observe(this){
            adapter.notifyDataSetChanged()
        }
        registerForContextMenu(binding.roomRecyclerView)

        binding.fabCreateRoom.setOnClickListener {
            val intent = Intent(this, CreateRoomActivity::class.java)
            startActivity(intent)
        }

        binding.buttonInvite.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.setOnClickListener(object: CustomDialog.ButtonClickListener {
                override fun onClicked(code: String) {
                    println("%%%"+code+"%%%")// 구현 후 삭제
                    // 코드 함수 수행
                }
            })
            dialog.myDig()
        }
    }
}