package com.example.sidehustle

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeSettingsBinding


class EmployeeSettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_employee_settings)

        val toolbar = findViewById<Toolbar>(R.id.employee_settings_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        binding.employeeSettingsRightIcon1.setOnClickListener{
            val context = it.context
            val intent = Intent(context,ChangeUsernameActivity::class.java)
            context.startActivity(intent)
        }
        binding.employeeSettingsRightIcon2.setOnClickListener{
            val context = it.context
            val intent = Intent(context,ChangePasswordActivity::class.java)
            context.startActivity(intent)
        }
        binding.employeeSettingsNotificationsSwitch.setOnCheckedChangeListener { buttonView, isChecked -> // Display a Toast when the Switch is checked
            if (isChecked) {
                switchToast("Notification is enabled")
            } else {
                switchToast("Notification is disabled")
            }
        }
        binding.employeeSettingsRightIcon5.setOnClickListener{
            val phoneNum = "+60129869029"
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(dialIntent)
        }
        binding.employeeSettingsRightIcon6.setOnClickListener{
            val context = it.context
            val intent = Intent(context,AboutSideHustleActivity::class.java)
            context.startActivity(intent)
        }
        binding.employeeSettingsLogoutIcon.setOnClickListener{
            val context = it.context
            val intent = Intent(context,MainActivity::class.java) //To be updated
            context.startActivity(intent)
        }
        binding.employeeSettingsLogoutTextview.setOnClickListener{
            val context = it.context
            val intent = Intent(context,MainActivity::class.java) //To be updated
            context.startActivity(intent)
        }
    }

    fun switchToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

// can override onOptionsItemSelected for custom behaviour
}