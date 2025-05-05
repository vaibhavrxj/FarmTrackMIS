package com.example.project

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSave: Button

    private val sharedPref: SharedPreferences by lazy {
        getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnSave = findViewById(R.id.btnSave)

        val savedName = sharedPref.getString("userName", "")
        val savedEmail = sharedPref.getString("email", "")
        val savedPhone = sharedPref.getString("phone", "")
        etName.setText(savedName)
        etEmail.setText(savedEmail)
        etPhone.setText(savedPhone)

        btnSave.setOnClickListener {
            saveProfileData()
        }
    }

    private fun saveProfileData() {
        val newName = etName.text.toString().trim()
        val newEmail = etEmail.text.toString().trim()
        val newPhone = etPhone.text.toString().trim()

        if (newName.isEmpty() || newEmail.isEmpty() || newPhone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }


        with(sharedPref.edit()) {
            putString("userName", newName)
            putString("email", newEmail)
            putString("phone", newPhone)
            apply()
        }

        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

        finish()
    }
}
