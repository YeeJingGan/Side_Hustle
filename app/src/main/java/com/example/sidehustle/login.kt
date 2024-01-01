package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val signupBtn: Button = findViewById(R.id.login_signupBtn)
        signupBtn.setOnClickListener{
            val Intent = Intent(this, register_start::class.java)
            startActivity(Intent)
        }

        val emailEditText: EditText = findViewById(R.id.login_email)
        val passwordEditText: EditText = findViewById(R.id.login_password)
        val continueBtn: Button = findViewById(R.id.login_continuteBtn)

        continueBtn.setOnClickListener {
            val enteredEmail = emailEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            // admin credentials
            val adminEmail = "admin@gmail.com"
            val adminPassword = "admin123"

            if (enteredEmail == adminEmail && enteredPassword == adminPassword) {
                // successful login, open admin page
                val adminIntent = Intent(this, AdminHomeActivity::class.java)
                startActivity(adminIntent)
                finish() // close login activity so user can't go back
            } else {
                // display an error message
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}