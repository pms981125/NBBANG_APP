package com.example.nbbang

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class NBBANGInfoDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig(string: String) {
        dialog.setContentView(R.layout.dialog_nbbang_info)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val text = dialog.findViewById<TextView>(R.id.textViewExplanation)
        val buttonOK = dialog.findViewById<Button>(R.id.btnOK)

        text.text = string

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
