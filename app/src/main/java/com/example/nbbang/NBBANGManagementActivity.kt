package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            //받은 room ID를 활용하게 변경
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.buttonDelete.setOnClickListener {
            //N빵 ID를 이용해 삭제 구현
        }

        binding.buttonModificate.setOnClickListener {
            val dialog = NBBANGDialog(this)
            dialog.setOnClickListener(object : NBBANGDialog.ButtonClickListener{
                override fun onClicked(period: String, money: String, explanation: String) {
                    println("%%%$period%%%$money%%%$explanation%%%")// 구현 후 삭제
                    // 서버로 전송
                }
            })
            dialog.myDig()
            dialog.changeText("수정 완료")
        }

        val adapterParticipants = ParticipantsAdapter(this, true)
        val recyclerParticipants = binding.recyclerViewParticipatingPeople
        recyclerParticipants.adapter = adapterParticipants
        recyclerParticipants.layoutManager = LinearLayoutManager(this)
        recyclerParticipants.setHasFixedSize(false)
        registerForContextMenu(recyclerParticipants)

        //예시
        val newUser = Participants("박민성")
        adapterParticipants.addUser(newUser)

        val adapterEntire = ParticipantsAdapter(this, false)
        val recyclerEntire = binding.recyclerViewEntirePeople
        recyclerEntire.adapter = adapterEntire
        recyclerEntire.layoutManager = LinearLayoutManager(this)
        recyclerEntire.setHasFixedSize(false)
        registerForContextMenu(recyclerEntire)

        //예시
        val newUser2 = Participants("황인수")
        adapterEntire.addUser(newUser2)
    }
}