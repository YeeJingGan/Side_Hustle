package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.my_butt).setOnClickListener {
            toEmployeeHome(it)
        }
    }

    fun toEmployeeHome(view: View){
        val context = view.context
        val intent = Intent(context,EmployerMyJobsActivity::class.java)

        context.startActivity(intent)
    }
}