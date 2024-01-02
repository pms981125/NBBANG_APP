package com.example.nbbang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nbbang.databinding.ActivityCreateRoomBinding

class CreateRoomActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCreateRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}