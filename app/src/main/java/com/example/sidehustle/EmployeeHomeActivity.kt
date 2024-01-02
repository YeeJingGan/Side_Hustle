package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView

class EmployeeHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_home)

        val listView = findViewById<ListView>(R.id.employee_home_job_list_view)

        val jobList: List<LegacyJob> = createJobList()
        val jobAdapter = LegacyJobAdapter(this, jobList)

        listView.adapter = jobAdapter

        setListeners()

        findViewById<BottomNavigationView>(R.id.employee_home_bottom_nav).apply {
            selectedItemId = R.id.bottom_nav_home_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_home_button -> false
                    R.id.bottom_nav_my_jobs_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyJobsActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyProfileActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }


    }

    private fun createJobList(): List<LegacyJob> {
// TODO: Replace with a real job list
        return mutableListOf(
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
        )
    }

    private fun setListeners() {
        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            toAnotherActivity(it, EmployeeHomeFavoriteJobsActivity::class.java)
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
//        finishAffinity()
    }
}