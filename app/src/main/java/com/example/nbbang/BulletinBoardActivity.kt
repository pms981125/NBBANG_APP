package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbbang.databinding.ActivityBulletinboardBinding

class BulletinBoardActivity:AppCompatActivity() {
    private lateinit var binding: ActivityBulletinboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBulletinboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentID = intent.getStringExtra("roomId")

        binding.nbbangImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val adapter = BulletinBoardAdapter(this)
        val recycler = binding.recyclerViewBulletinBoard
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(false)
        registerForContextMenu(recycler)

        binding.buttonWrite.setOnClickListener {
            val username = "박민성" //임시
            val content = binding.editTextBoard.text.toString()

            if (content == "") {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                val newItem = BulletinBoardItem(username, content)
                adapter.addItem(newItem)
            }
        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomID", intentID)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}