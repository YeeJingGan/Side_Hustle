package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        binding.passwordImageButton3.setOnClickListener {
            finish()
        }

        binding.passwordButton2.setOnClickListener{
            val text = "Username successfully updated"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(this,text,duration)
            toast.show()
            finish()
        }

    }
}