package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sidehustle.databinding.ActivityMainBinding
//import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var employers: List<EntityEmployer> = emptyList()
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        binding.apply{
            sideHustleMainButton1.setOnClickListener {
                startActivity(Intent(this@MainActivity,EmployeeHomeActivity::class.java))
            }
            sideHustleMainButton2.setOnClickListener {
                startActivity(Intent(this@MainActivity,EmployerHomeActivity::class.java))
            }
            sideHustleMainButton3.setOnClickListener {
                startActivity(Intent(this@MainActivity,AdminHomeActivity::class.java))
            }
            sideHustleMainButton4.setOnClickListener {
                startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            }
        }


    }

}