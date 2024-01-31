package com.example.nbbang

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParticipatingPeopleDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {
        dialog.setContentView(R.layout.dialog_participating_people)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val adapter = ParticipatingPeopleDialogAdapter(dialog.context)
        val recycler:RecyclerView = dialog.findViewById(R.id.recyclerViewParticipationPeopleDialog)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(dialog.context)
        recycler.setHasFixedSize(false)
        //registerForContextMenu(recycler) //메뉴용

        val temp = ParticipatingPeople("박민성")
        val temp2 = ParticipatingPeople("황인수")

        adapter.addUser(temp)
        adapter.addUser(temp2)

        val buttonOK = dialog.findViewById<Button>(R.id.buttonOK)
        buttonOK.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked()
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }
}