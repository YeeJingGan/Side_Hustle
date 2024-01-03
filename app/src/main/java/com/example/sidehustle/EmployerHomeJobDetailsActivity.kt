package com.example.sidehustle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sidehustle.databinding.ActivityEmployerHomeJobDetailsBinding
import kotlinx.coroutines.launch

class EmployerHomeJobDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeJobDetailsBinding
    lateinit var viewModel: EmployerHomeJobDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home_job_details)

        val intent = intent
        val jobID = intent.getLongExtra("jobID", -100)


        Log.i("INTENT", jobID.toString())
        Toast.makeText(this, "HI HI ID IS $jobID", Toast.LENGTH_SHORT).show()
        // Test PASSED

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployerHomeJobDetailsViewModel::class.java)

        if (jobID.equals(-100)) {
            finish()
        }

        viewModel.viewModelScope.launch {
            binding.job = viewModel.getJobByJobID(jobID)
            binding.employer = viewModel.getEmployerByJobID(jobID)
        }

        viewModel.starCount.observe(this) {
            Log.i("AVG",it.toString())
            updateStarColors(it)
        }

        viewModel.getAverageRatingByJobIDAndCommenter(jobID,"EMPLOYEE")

        setup()
        setListeners()

    }

    private fun setup() {
        setSupportActionBar(binding.employerHomeJobDetailsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun setListeners() {
        // TODO : DELETE POST AND CLOSE APPLICATION RMB
        binding.employerJobDetailsButtonClose.setOnClickListener {
            Toast.makeText(this, "HEHEHE NOT YET DO CLOSE APPLICATION", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.employerJobDetailsButtonDelete.setOnClickListener {
            Toast.makeText(this, "HEHEHE NOT YET DO DELETE", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employerJobDetailsStar1,
            binding.employerJobDetailsStar2,
            binding.employerJobDetailsStar3,
            binding.employerJobDetailsStar4,
            binding.employerJobDetailsStar5
        )

        for (i in 0..<stars.size) {
            if (i < starsCount) {
                stars[i].setImageResource(R.drawable.ic_star_24px)
                stars[i].setColorFilter(Color.parseColor("#FDB915"))
            } else {
                stars[i].setImageResource(R.drawable.ic_star_hollow_24px)
                stars[i].setColorFilter(Color.parseColor("#000000"))
            }
        }
    }
}