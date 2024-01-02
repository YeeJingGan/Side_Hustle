package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AdminHomeActivity : AppCompatActivity(), AdminJobsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        // replace the fragment when the activity is created
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, AdminJobsFragment.newInstance("", ""))
            .commit()

        // set click listener for imageButtons in the footer
        findViewById<ImageButton>(R.id.footer_ic_home).setOnClickListener {
            onButtonClicked(R.id.footer_ic_home)
        }
        findViewById<ImageButton>(R.id.footer_ic_my_jobs).setOnClickListener {
            onButtonClicked(R.id.footer_ic_my_jobs)
        }
        findViewById<ImageButton>(R.id.footer_ic_review).setOnClickListener {
            onButtonClicked(R.id.footer_ic_review)
        }
        findViewById<ImageButton>(R.id.footer_ic_settings).setOnClickListener {
            onButtonClicked(R.id.footer_ic_settings)
        }
    }

    override fun onButtonClicked(buttonId: Int) {
        // when footer imageButton clicked, switch fragments/ open activity
        when (buttonId) {
            R.id.footer_ic_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, AdminJobsFragment.newInstance("", ""))
                    .commit()
            }
            R.id.footer_ic_my_jobs -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, AdminApproveJobsFragment.newInstance("", ""))
                    .commit()
            }
            R.id.footer_ic_review -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, AdminReviewCommentFragment.newInstance("", ""))
                    .commit()
            }
            R.id.footer_ic_settings -> {
                /*supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, AdminSettingsFragment.newInstance("", ""))
                    .commit()*/
                val settingsIntent = Intent(this, AdminSettingsActivity::class.java)
                startActivity(settingsIntent)
            }
        }
    }
}
