package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashBoard : AppCompatActivity() {

    private var isFabOpen = true

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val powerLiftingCard: CardView = findViewById(R.id.pc)
        val cardioCard: CardView = findViewById(R.id.cd)
        val bodyBuildingCard: CardView = findViewById(R.id.cb)
        val calisthenicsCard: CardView = findViewById(R.id.cc)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        powerLiftingCard.setOnClickListener {
            navigateToActivity(PowerLifting::class.java)
        }
        cardioCard.setOnClickListener {
            navigateToActivity(Cardio::class.java)
        }
        bodyBuildingCard.setOnClickListener {
            navigateToActivity(BodyBuilding::class.java)
        }
        calisthenicsCard.setOnClickListener {
            navigateToActivity(Calsinthenics::class.java)
        }

        val mainFab = findViewById<FloatingActionButton>(R.id.fab)
        val dataFab = findViewById<FloatingActionButton>(R.id.fab_Data)

        dataFab.visibility = View.VISIBLE

        mainFab.setOnClickListener {
            if (isFabOpen) {
                closeFabMenu(dataFab)
            } else {
                openFabMenu(dataFab)
            }
        }

        dataFab.setOnClickListener {
            navigateToActivity(MainActivity::class.java)
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navigateToActivity(Home::class.java)
                    true
                }
                R.id.navigation_location -> {
                    navigateToActivity(Location::class.java)
                    true
                }
                R.id.navigation_profile -> {
                    navigateToActivity(Profile::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun openFabMenu(dataFab: FloatingActionButton) {
        dataFab.visibility = View.VISIBLE
        isFabOpen = true
    }

    private fun closeFabMenu(dataFab: FloatingActionButton) {
        dataFab.visibility = View.GONE
        isFabOpen = false
    }
}
