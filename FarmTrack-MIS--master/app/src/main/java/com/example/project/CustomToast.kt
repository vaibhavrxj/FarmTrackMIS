package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.project.R

object CustomToast {
    fun showToast(context: Context, message: String) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        val text: TextView = layout.findViewById(R.id.toastText)
        text.text = message

        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

}
