package com.harsha.buildlinkbetweenadeptandcompanies

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivitySignupBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_PROFILE_FOLDER
import com.harsha.buildlinkbetweenadeptandcompanies.utils.uploadImage
import com.squareup.picasso.Picasso

class signup_activity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) {
                if (it == null) {
                    Toast.makeText(this@signup_activity, "Choose another image", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT
        user = User()
        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.signupbtn.text = "Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get().addOnSuccessListener {
                    user = it.toObject<User>()!!
                    if (!user.image.isNullOrEmpty()) {
                        Picasso.get().load(user.image).into(binding.profileImage)
                    }
                        binding.name.editText?.setText(user.name)
                        binding.email.editText?.setText(user.email)
                        binding.password.editText?.setText(user.password)
                }
            }
        }
        binding.signupbtn.setOnClickListener {
            if (intent.hasExtra("MODE"))
            {
                if (intent.getIntExtra("MODE",-1)==1)
                {
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user).addOnSuccessListener {
                            Toast.makeText(
                                this@signup_activity,
                                "Profile updated",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            startActivity(
                                Intent(
                                    this@signup_activity,
                                    UpdateActivity::class.java
                                )
                            )
                            finish()
                        }
                }
            }
            else {
                if (binding.name.editText?.text.toString()
                        .equals("") or binding.password.editText?.text.toString()
                        .equals("") or binding.email.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        this@signup_activity,
                        "Please fill all the information",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.name.editText?.text.toString()
                            user.email = binding.email.editText?.text.toString()
                            user.password = binding.password.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        this@signup_activity,
                                        "Profile created",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    startActivity(
                                        Intent(
                                            this@signup_activity,
                                            DetailsActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        } else {
                            Toast.makeText(
                                this@signup_activity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        binding.addimage.setOnClickListener {
            launcher.launch("image/*")
        }
    }
}