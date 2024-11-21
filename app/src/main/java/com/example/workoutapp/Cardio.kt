package com.example.workoutapp
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.example.workoutapp.R

class Cardio : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardio)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Cardio "
            setDisplayHomeAsUpEnabled(true)
        }


        val squatJumpVideoUrl = "https://www.youtube.com/watch?v=MxfTNXSFiYI"
        val benchPressVideoUrl = "https://www.youtube.com/watch?v=AduT4Eq-iP0"
        val deadliftVideoUrl = "https://www.youtube.com/watch?v=yPqv3ejnZvc"

        val squatJumpVideoBtn: ImageButton = findViewById(R.id.squatVideoBtn)
        val benchPressVideoBtn: ImageButton = findViewById(R.id.benchPressvideoBtn)
        val deadliftVideoBtn: ImageButton = findViewById(R.id.deadliftVvdeoBtn)

        squatJumpVideoBtn.setOnClickListener {
            openYouTubeVideo(squatJumpVideoUrl)
        }

        benchPressVideoBtn.setOnClickListener {
            openYouTubeVideo(benchPressVideoUrl)
        }

        deadliftVideoBtn.setOnClickListener {
            openYouTubeVideo(deadliftVideoUrl)
        }
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
