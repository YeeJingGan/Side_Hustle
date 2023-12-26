package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners(){
        findViewById<Button>(R.id.my_butt).setOnClickListener {
            toAnotherActivity(it,EmployerMyJobsOngoingActivity::class.java)
        }

        findViewById<Button>(R.id.my_butt2).setOnClickListener{
            toAnotherActivity(it,EmployerHomeUploadJobsActivity::class.java)
        }

        findViewById<Button>(R.id.mybut3).setOnClickListener{
            toAnotherActivity(it,EmployerMyJobsOngoingEmployeeListActicity::class.java)
        }
        findViewById<Button>(R.id.mybut4).setOnClickListener{
            toAnotherActivity(it,EmployerMyJobsOngoingEmployeeListEmployeeDetailsActivity::class.java)
        }
    }
    private fun toAnotherActivity(view:View, destinationActivity: Class<*>){
        view.context.let{
            val intent = Intent(it,destinationActivity)
            it.startActivity(intent)
        }
    }
}