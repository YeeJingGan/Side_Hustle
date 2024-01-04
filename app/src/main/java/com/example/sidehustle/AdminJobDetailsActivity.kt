package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.MaterialToolbar
import androidx.appcompat.widget.Toolbar

class AdminJobDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_job_details)

        val toolbar = findViewById<Toolbar>(R.id.admin_home_job_details_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_30px)
            setDisplayShowTitleEnabled(false)
        }
    }
}