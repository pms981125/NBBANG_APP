package com.example.nbbang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
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

        // ViewModel에서 LiveData를 구독
        viewModel.itemClickEvent.observe(this, Observer { clickedPosition ->
            // 클릭된 아이템의 위치 정보(clickedPosition)를 사용하여 원하는 동작 수행
            // 예: 클릭된 아이템의 내용을 로그에 출력
            //Log.d("RoomAdapter", "Item clicked at position $clickedPosition")
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("title", viewModel.items[clickedPosition].title)
            intent.putExtra("contents", viewModel.items[clickedPosition].contents)
            startActivity(intent)
        })

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

        binding.buttonSearch.setOnClickListener {
            val intent = Intent(this, SearchPartyActivity::class.java)
            startActivity(intent)
        }
    }
}