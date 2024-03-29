package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbbang.databinding.ActivityRoomBinding
import kotlin.random.Random

class RoomActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        var intentID = intent.getStringExtra("id") //val로 변경
        val intentTitle = intent.getStringExtra("title")
        val intentContents = intent.getStringExtra("contents")
        val intentPublic = intent.getBooleanExtra("public", false)

        binding.nbbangImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.textViewTitle.text = intentTitle
        binding.textViewInfo.text = intentContents
        if (intentPublic) {
            binding.textViewPublic.text = "이 방은 공개 방입니다"
            binding.textViewPublic.setTextColor(resources.getColor(R.color.theme))
        }
        else {
            binding.textViewPublic.text = "이 방은 비공개 방입니다"
            binding.textViewPublic.setTextColor(resources.getColor(R.color.lightred))
        }
        binding.textViewInviteCode.text = generateRandomString(20)

        binding.buttonParticipate.setOnClickListener {
            //파티 참여 구현
        }

        binding.buttonWithdrawal.setOnClickListener {
            //파티 탈퇴 구현
        }

        binding.buttonModification.setOnClickListener {
            val dialog = ModificationDialog(this)
            dialog.setOnClickListener(object : ModificationDialog.ButtonClickListener {
                override fun onClicked(title: String, info: String, public: Boolean) {
                    binding.textViewTitle.text = title
                    binding.textViewInfo.text = info
                    if (public) {
                        binding.textViewPublic.text = "이 방은 공개 방입니다"
                        binding.textViewPublic.setTextColor(resources.getColor(R.color.theme))
                    }
                    else {
                        binding.textViewPublic.text = "이 방은 비공개 방입니다"
                        binding.textViewPublic.setTextColor(resources.getColor(R.color.lightred))
                    }
                }
            })
            dialog.myDig()
        }

        binding.buttonParticipationPeople.setOnClickListener {
            val dialog = ParticipatingPeopleDialog(this)
            dialog.myDig()
        }

        val adapter = NBBANGAdapter(this)
        val recycler = binding.recyclerViewNBBANG
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(false)
        registerForContextMenu(recycler)

        binding.buttonMakeNBBANG.setOnClickListener {
            val dialog = NBBANGDialog(this)
            dialog.setOnClickListener(object : NBBANGDialog.ButtonClickListener{
                override fun onClicked(period: String, money: String, explanation: String) {
                    println("%%%$period%%%$money%%%$explanation%%%")// 구현 후 삭제
                    // 코드 함수 수행
                    val newItem = NBBANGItem(period, money, explanation)

                    adapter.addItem(newItem)
                }
            })
            dialog.myDig()
        }

        binding.buttonBulletinBoard.setOnClickListener {
            val intent = Intent(this, BulletinBoardActivity::class.java)
            //임시
            intentID = "1"
            intent.putExtra("roomID", intentID)
            startActivity(intent)
        }
    }

    private fun generateRandomString(length: Int): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') // 원하는 문자 범위 지정

        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}