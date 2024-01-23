package com.example.nbbang

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView

class ModificationDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {
        dialog.setContentView(R.layout.dialog_modification)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val buttonComplete = dialog.findViewById<Button>(R.id.buttonComplete)
        val buttonCancel = dialog.findViewById<ImageView>(R.id.imageView4)

        buttonComplete.setOnClickListener {
            val title = dialog.findViewById<EditText>(R.id.editTextTitle).text.toString()
            val info = dialog.findViewById<EditText>(R.id.editTextInfo).text.toString()
            val public = dialog.findViewById<CheckBox>(R.id.checkBoxIsPublic).isChecked

            onClickListener.onClicked(title, info, public)

            dialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(title: String, info: String, public: Boolean)
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }
}