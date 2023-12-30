package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerHomeBinding

class EmployerHomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmployerHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_home)

    }
}