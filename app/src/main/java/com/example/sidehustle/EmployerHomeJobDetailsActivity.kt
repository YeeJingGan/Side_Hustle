package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerHomeJobDetailsBinding

class EmployerHomeJobDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeJobDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_home_job_details)

        val intent = intent
        val jobID = intent.getLongExtra("jobID",-100)
        Log.i("INTENT",jobID.toString())
        Toast.makeText(this,"HIHI IND IS $jobID",Toast.LENGTH_SHORT).show()
        // Test PASSED

        setSupportActionBar(binding.employerHomeJobDetailsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        // TODO : DELETE POST AND CLOSE APPLICATION RMB
        binding.employerJobDetailsButtonClose.setOnClickListener{
            Toast.makeText(this,"HEHEHE NOT YET DO CLOSE APPLICATION",Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.employerJobDetailsButtonDelete.setOnClickListener{
            Toast.makeText(this,"HEHEHE NOT YET DO DELETE",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}