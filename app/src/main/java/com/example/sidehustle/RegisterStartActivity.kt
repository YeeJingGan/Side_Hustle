package com.example.sidehustle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import androidx.cardview.widget.CardView

class RegisterStartActivity : AppCompatActivity() {
    lateinit var register_bgVideo: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_start)

        //To set the background video
        val bgVideoUri: Uri = Uri.parse("android.resource://$packageName/${R.raw.register_bgvideo}")

        register_bgVideo = findViewById(R.id.register_bgVideo)

        register_bgVideo.setVideoURI(bgVideoUri)
        register_bgVideo.requestFocus()
        register_bgVideo.start()
        register_bgVideo.setOnPreparedListener { it.isLooping = true }

        //If press employee/employer card views, direct to register_user activity
        val register_employeeCard: CardView = findViewById(R.id.register_employeeCard)
        val register_employerCard: CardView = findViewById(R.id.register_employerCard)
        register_employeeCard.setOnClickListener{
            val Intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(Intent)
        }
        register_employerCard.setOnClickListener{
            val Intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(Intent)
        }
    }
}