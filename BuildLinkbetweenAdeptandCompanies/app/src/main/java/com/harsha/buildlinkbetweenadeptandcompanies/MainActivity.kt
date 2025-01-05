package com.harsha.buildlinkbetweenadeptandcompanies

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor=Color.BLACK
Handler(Looper.getMainLooper()).postDelayed({
    if(FirebaseAuth.getInstance().currentUser==null) {
        startActivity(Intent(this@MainActivity, user_activity::class.java))
    }
    else{
        startActivity(Intent(this@MainActivity,HomeActivity::class.java))
    }
},1500)
    }
}