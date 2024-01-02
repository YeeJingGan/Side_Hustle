package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        binding.mainButton3.setOnClickListener {
            val database = SideHustleDatabase.getDatabase(applicationContext)
            val employeeDao = database.employeeDao()

            val employee = EntityEmployee(
                0,
                "username1",
                "abc@mail.com",
                "abc123",
                byteArrayOf(0x48, 101, 108, 108, 111)
            )

            GlobalScope.launch(Dispatchers.IO) {
                employeeDao.insert(employee)
            }

        }
    }

    private fun setListenerAndToAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(it.context, destinationActivity))
        }
    }
}