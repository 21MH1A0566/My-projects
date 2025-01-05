package com.harsha.buildlinkbetweenadeptandcompanies

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class user_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        window.statusBarColor=Color.TRANSPARENT
        val signupbutton = findViewById<Button>(R.id.signup_btn)
        signupbutton.setOnClickListener {
            val Intent = Intent(this, signup_activity::class.java)
            startActivity(Intent)
        }
        val loginbutton = findViewById<Button>(R.id.login_btn)
        loginbutton.setOnClickListener {
            val Intent = Intent(this, login_activity::class.java)
            startActivity(Intent)
        }
    }
    }