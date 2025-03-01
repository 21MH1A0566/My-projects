package com.harsha.buildlinkbetweenadeptandcompanies

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.TopAppBar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityLoginBinding

class login_activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor= Color.TRANSPARENT
        binding.loginbtn.setOnClickListener {
            if (binding.email.editText?.text.toString()
                    .equals("") or binding.password.editText?.text.toString().equals("")
            ) {
                Toast.makeText(
                    this@login_activity,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var user = User(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()

                )
                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@login_activity,
                                "Log in Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@login_activity, HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@login_activity,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}