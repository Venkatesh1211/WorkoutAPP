package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val startWorkoutButton = findViewById<Button>(R.id.startWorkoutButton)

        startWorkoutButton.setOnClickListener {
            val intent = Intent(this,DashBoard::class.java)
            startActivity(intent)
        }
    }
}
