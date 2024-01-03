package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, EmployeeHomeActivity::class.java))
        }
        binding.btn2.setOnClickListener {
            startActivity(Intent(this, EmployerHomeActivity::class.java))
        }

        binding.btn3.setOnClickListener {
            startActivity(Intent(this, AdminHomeActivity::class.java))
        }

        binding.btn4.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}