package com.example.mad_jrom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val signupBtn: Button = findViewById(R.id.login_signupBtn)
        signupBtn.setOnClickListener{
            val Intent = Intent(this, register_start::class.java)
            startActivity(Intent)
        }
    }
}