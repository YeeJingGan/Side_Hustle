package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerHomeUploadJobsBinding

class EmployerHomeUploadJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeUploadJobsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home_upload_jobs)

        setSupportActionBar(binding.employerUploadJobToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        var wagesAmount = 0
        updateWages(wagesAmount)

        binding.uploadJobButtonAdd.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
            Log.i("ADD","ADDED AMOUNT TO $wagesAmount")
        }

        binding.uploadJobButtonMinus.setOnClickListener {
            if (wagesAmount >= 0) {
                wagesAmount -= 10
                updateWages(wagesAmount)
            }
            Log.i("ADD","ADDED AMOUNT TO $wagesAmount")
        }

        Log.i("ADD","ADDED AMOUNT???")

    }

    private fun updateWages(amount: Int) {
        binding.uploadJobInputWages.text = "RM ${amount}"
    }
}