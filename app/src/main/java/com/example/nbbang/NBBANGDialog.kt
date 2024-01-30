package com.example.nbbang

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class NBBANGDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {
        dialog.setContentView(R.layout.dialog_nbbang)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val buttonCreate = dialog.findViewById<Button>(R.id.buttonCreate)
        val buttonCancel = dialog.findViewById<ImageView>(R.id.imageView4)

        buttonCreate.setOnClickListener {
            val period = dialog.findViewById<EditText>(R.id.editTextPeriod).text.toString()
            val money = dialog.findViewById<EditText>(R.id.editTextMoney).text.toString()
            val explanation = dialog.findViewById<EditText>(R.id.editTextExplanation).text.toString()

            onClickListener.onClicked(period, money, explanation)

            dialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(period: String, money: String, explanation: String)
    }

    fun changeText(text: String) {
        val button = dialog.findViewById<Button>(R.id.buttonCreate)
        button.text = text
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }
}