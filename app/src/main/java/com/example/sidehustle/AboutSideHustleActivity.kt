package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityAboutSideHustleBinding

class AboutSideHustleActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutSideHustleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_about_side_hustle)

        binding.imageButton4.setOnClickListener {
            finish()
        }

    }
}