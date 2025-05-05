package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etMobile: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCaptcha: EditText
    private lateinit var tvCaptcha: TextView
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private var generatedCaptcha: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etMobile = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        etCaptcha = findViewById(R.id.etCaptcha)
        tvCaptcha = findViewById(R.id.tvCaptcha)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        generateCaptcha()
        tvCaptcha.setOnClickListener {
            generateCaptcha()
        }
        btnLogin.setOnClickListener {
            validateLogin()
        }
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun generateCaptcha() {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        generatedCaptcha = (1..5)
            .map { chars.random() }
            .joinToString("")
        tvCaptcha.text = generatedCaptcha
    }

    private fun validateLogin() {
        val mobile = etMobile.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val captchaInput = etCaptcha.text.toString().trim()

        if (mobile.isEmpty() || password.isEmpty() || captchaInput.isEmpty()) {
            CustomToast.showToast(this, "All fields are required!")
            return
        }

        if (captchaInput != generatedCaptcha) {
            CustomToast.showToast(this, "Captcha does not match!")
            generateCaptcha()
            return
        }
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedMobile = sharedPref.getString("mobile", "")
        val savedPassword = sharedPref.getString("password", "")

        if (mobile == savedMobile && password == savedPassword) {
            CustomToast.showToast(this, "Login Successful")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            CustomToast.showToast(this, "Invalid Credentials")
        }
    }
}
