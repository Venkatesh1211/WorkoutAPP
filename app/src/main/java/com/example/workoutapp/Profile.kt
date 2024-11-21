package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val profileImage: ImageView = findViewById(R.id.profileImage)
        val tvUsername: TextView = findViewById(R.id.tvUsername)

        val tvHeight: TextView = findViewById(R.id.tvHeight)
        val tvWeight: TextView = findViewById(R.id.tvWeight)
        val tvAge: TextView = findViewById(R.id.tvAge)

        val tvHeartbeat: TextView = findViewById(R.id.tvHeartbeat)
        val tvCalories: TextView = findViewById(R.id.tvCalories)
        val tvSleep: TextView = findViewById(R.id.tvSleep)

        val pbSquats: ProgressBar = findViewById(R.id.pbSquats)
        val pbDeadlift: ProgressBar = findViewById(R.id.pbDeadlift)
        val pbCardio: ProgressBar = findViewById(R.id.pbCardio)
        val pbPullUps: ProgressBar = findViewById(R.id.pbPullUps)
        val pbChest:ProgressBar = findViewById(R.id.pbchest)

        val btnLogout: Button = findViewById(R.id.btnLogout)

        profileImage.setImageResource(R.drawable.img_26)

        tvUsername.text = "John Smith"
        tvHeight.text = "Height: 175 cm"
        tvWeight.text = "Weight: 70 kg"
        tvAge.text = "Age: 25"

        tvHeartbeat.text = "75 bpm"
        tvCalories.text = "2000 kcal"
        tvSleep.text = "8 hrs"

        pbSquats.progress = 80
        pbDeadlift.progress = 65
        pbCardio.progress = 90
        pbPullUps.progress = 70
        pbChest.progress = 75

        profileImage.setOnClickListener {
            Toast.makeText(this, "Profile image clicked", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}
