package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class BodyBuilding : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_building)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Body Building "
            setDisplayHomeAsUpEnabled(true)
        }

        val bentVideoUrl = "https://www.youtube.com/watch?v=6gvmcqr226U"
        val benchPressVideoUrl ="https://www.youtube.com/watch?v=CFBZ4jN1CMI"
        val deadliftVideoUrl = "https://www.youtube.com/watch?v=NAIEnMjN-6w"


        val bentVideoBtn: ImageButton = findViewById(R.id.bent)
        val benchPressVideoBtn: ImageButton = findViewById(R.id.benchPressvideoBtn)
        val deadliftVideoBtn: ImageButton = findViewById(R.id.deadliftVvdeoBtn)

        bentVideoBtn.setOnClickListener { openYouTubeVideo(bentVideoUrl) }
        benchPressVideoBtn.setOnClickListener { openYouTubeVideo(benchPressVideoUrl) }
        deadliftVideoBtn.setOnClickListener { openYouTubeVideo(deadliftVideoUrl) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun openYouTubeVideo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
