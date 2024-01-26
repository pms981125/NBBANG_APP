package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nbbang.databinding.ActivityNbbangManagementBinding

class NBBANGManagementActivity:AppCompatActivity() {
    private lateinit var binding: ActivityNbbangManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNbbangManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nbbangImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}