package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupTextView: TextView
    private lateinit var dbHelper: DBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        signupTextView = findViewById(R.id.tvSignup)
        dbHelper = DBHelper(this)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                if (username.isEmpty()) {
                    usernameEditText.error = "Username cannot be empty"
                }
                if (password.isEmpty()) {
                    passwordEditText.error = "Password cannot be empty"
                }
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                usernameEditText.error = "Please enter a valid email address"
                return@setOnClickListener
            }

            if (password.length < 8) {
                passwordEditText.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }

            if (dbHelper.checkUser(username, password)) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }
        }

        signupTextView.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener{
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }
    }
}
