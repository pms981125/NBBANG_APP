package com.example.nbbang

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {
        dialog.setContentView(R.layout.dialog_invite)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val btnOK = dialog.findViewById<Button>(R.id.buttonOK)
        val btnCancel = dialog.findViewById<ImageView>(R.id.imageViewClose)

        btnOK.setOnClickListener {
            val code = dialog.findViewById<EditText>(R.id.editTextInviteCode).text.toString()
            onClickListener.onClicked(code)
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(code: String)
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }

    /*fun changeText(text: String) {
        val textView = dialog.findViewById<TextView>(R.id.textView6)
        textView.text = text
    }*/
}