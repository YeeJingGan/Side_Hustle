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

        findViewById<Button>(R.id.button).setOnClickListener {
            toEmployeeHome(it)
        }
    }

    fun toEmployeeHome(view: View){
        val context = view.context
        val intent = Intent(context,EmployeeHomeActivity::class.java)

        context.startActivity(intent)
    }
}