package com.example.workoutapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    private lateinit var db: AppDatabase
    private lateinit var personDao: PersonDao

    private lateinit var firstNameEditText: EditText
    private lateinit var ageSpinner: Spinner
    private lateinit var feetSpinner: Spinner
    private lateinit var inchSpinner: Spinner
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var goalRadioGroup: RadioGroup
    private lateinit var addButton: Button

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "workout-database")
            .build()
        personDao = db.personDao()

        firstNameEditText = findViewById(R.id.firstNameEditText)
        ageSpinner = findViewById(R.id.ageSpinner)
        feetSpinner = findViewById(R.id.feetSpinner)
        inchSpinner = findViewById(R.id.inchSpinner)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        goalRadioGroup = findViewById(R.id.fitnessGoalRadioGroup)
        addButton = findViewById(R.id.addButton)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        personAdapter = PersonAdapter(emptyList()) { person ->
            deletePerson(person)
        }
        recyclerView.adapter = personAdapter

        setupSpinners()

        addButton.setOnClickListener {
            addPerson()
        }

        loadPersons()
    }

    private fun setupSpinners() {
        val ageOptions = (16..100).map { it.toString() }
        ageSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageOptions)

        val feetOptions = (1..7).map { "$it'" }
        feetSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, feetOptions)

        val inchOptions = (0..11).map { "$it\"" }
        inchSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, inchOptions)
    }

    private fun addPerson() {
        val firstName = firstNameEditText.text.toString()
        val age = ageSpinner.selectedItem?.toString()?.toIntOrNull() ?: 0
        val feet = feetSpinner.selectedItem?.toString()?.replace("'", "")?.toIntOrNull() ?: 0
        val inches = inchSpinner.selectedItem?.toString()?.replace("\"", "")?.toIntOrNull() ?: 0
        val heightInCm = (feet * 30.48 + inches * 2.54).toFloat()

        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val gender = if (selectedGenderId != -1) {
            findViewById<RadioButton>(selectedGenderId).text.toString()
        } else {
            "Not Specified"
        }

        val selectedGoalId = goalRadioGroup.checkedRadioButtonId
        val goal = if (selectedGoalId != -1) {
            findViewById<RadioButton>(selectedGoalId).text.toString()
        } else {
            "Not Specified"
        }

        val weight = 70f
        val bmi = if (heightInCm > 0) weight / ((heightInCm / 100) * (heightInCm / 100)) else 0f

        val newPerson = Person(
            name = firstName,
            age = age,
            height = heightInCm,
            weight = weight,
            gender = gender,
            goal = goal,
            bmi = bmi
        )

        scope.launch {
            withContext(Dispatchers.IO) {
                personDao.insert(newPerson)
            }
            loadPersons()
        }
    }

    private fun loadPersons() {
        scope.launch {
            val persons = withContext(Dispatchers.IO) {
                personDao.getAllPersons()
            }
            personAdapter.updatePersons(persons)
        }
    }

    private fun deletePerson(person: Person) {
        scope.launch {
            withContext(Dispatchers.IO) {
                personDao.delete(person)
            }
            loadPersons()
        }
    }
}
