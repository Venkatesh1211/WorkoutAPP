package com.example.workoutapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var weight: Float,
    var height: Float,
    var age: Int,
    var gender: String,
    var calories: Int = 2000,
    var bmi: Float = 0f,
    var goal: String,
    var dailyCalorieIntake: Int = 2500,
    var workoutFrequency: Int = 3,
    var lastWorkoutDate: String = "2024-11-08"
)

