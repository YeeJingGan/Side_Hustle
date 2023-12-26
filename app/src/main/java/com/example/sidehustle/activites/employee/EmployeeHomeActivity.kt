package com.example.sidehustle.activites.employee

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.window.OnBackInvokedDispatcher
import androidx.core.content.ContextCompat
import com.example.sidehustle.Job
import com.example.sidehustle.JobAdapter
import com.example.sidehustle.R

class EmployeeHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_home)

        val listView = findViewById<ListView>(R.id.employee_home_job_list_view)

        val jobList: List<Job> = createJobList()
        val jobAdapter = JobAdapter(this, jobList)

        listView.adapter = jobAdapter

        setIconColor()
        setListeners()
    }

    private fun createJobList(): List<Job> {
// TODO: Replace with a real job list 
        return mutableListOf(
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
        )
    }

    private fun setIconColor() {
        findViewById<ImageButton>(R.id.home_button).setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.themeColor
            ), PorterDuff.Mode.SRC_IN
        )
    }

    private fun setListeners() {
        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            toAnotherActivity(it, EmployeeFavoriteJobsActivity::class.java)
        }

        findViewById<ImageButton>(R.id.my_jobs_button).setOnClickListener {
            finish()
            toAnotherActivity(it, EmployeeJobDetailsActivity::class.java)
        }

        findViewById<ImageButton>(R.id.my_profile_button).setOnClickListener {
            finish()
            toAnotherActivity(it, EmployeeMyProfileActivity::class.java)
        }
    }

    private fun toAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.context.also {
            val intent = Intent(it, destinationActivity)
            it.startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}