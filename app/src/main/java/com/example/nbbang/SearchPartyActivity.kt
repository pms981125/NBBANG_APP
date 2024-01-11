package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbbang.databinding.ActivitySearchPartyBinding

class SearchPartyActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySearchPartyBinding
    private val viewModel = SearchViewModel()
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nbbangImageView.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val recyclerView = binding.recyclerView
        adapter = SearchAdapter(viewModel, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        viewModel.itemsListData.observe(this) {
            adapter.notifyDataSetChanged()
        }
        registerForContextMenu(binding.recyclerView)
    }
}