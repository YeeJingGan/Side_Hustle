package com.example.sidehustle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityChangeUsernameBinding

class ChangeUsernameActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeUsernameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_change_username)

        binding.imageButton3.setOnClickListener {
            finish()
        }

        binding.button2.setOnClickListener{
            val text = "Username successfully updated"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(this,text,duration)
            toast.show()
            finish()
        }
    }
}