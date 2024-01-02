package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            setListenerAndToAnotherActivity(mainButton1, EmployerHomeActivity::class.java)
            setListenerAndToAnotherActivity(mainButton2, EmployeeHomeActivity::class.java)

        }
    }

    private fun setListenerAndToAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(it.context, destinationActivity))
        }
    }
}