package com.example.sidehustle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyProfileSettingsBinding

class EmployerMyProfileSettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyProfileSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_employer_my_profile_settings)

        val toolbar = findViewById<Toolbar>(R.id.employer_settings_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        binding.employerSettingsRightIcon2.setOnClickListener{
            val context = it.context
            val intent = Intent(context,ChangePasswordActivity::class.java)
            context.startActivity(intent)
        }
        binding.employerSettingsNotificationsSwitch.setOnCheckedChangeListener{ buttonView, isChecked -> // Display a Toast when the Switch is checked
            if (isChecked) {
                switchToast("Notification is enabled")
            } else {
                switchToast("Notification is disabled")
            }
        }
        binding.employerSettingsRightIcon5.setOnClickListener{
            val phoneNum = "+60129869029"
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(dialIntent)
        }
        binding.employerSettingsRightIcon6.setOnClickListener{
            val context = it.context
            val intent = Intent(context,AboutSideHustleActivity::class.java) //To be updated
            context.startActivity(intent)
        }
        binding.employerSettingsLogoutIcon.setOnClickListener{
            val context = it.context
            val intent = Intent(context,MainActivity::class.java) //To be updated
            context.startActivity(intent)
        }
        binding.employerSettingsLogoutTextview.setOnClickListener{
            val context = it.context
            val intent = Intent(context,MainActivity::class.java) //To be updated
            context.startActivity(intent)
        }
    }
    fun switchToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}