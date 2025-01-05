package com.harsha.buildlinkbetweenadeptandcompanies.post

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.HomeActivity
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Post
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityPostBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.POST_NODE
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_POST_FOLDER
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_PROFILE_FOLDER
import com.harsha.buildlinkbetweenadeptandcompanies.utils.uploadImage


class PostActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    private var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
        }
        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.postButton.setOnClickListener {
                    val post: Post =
                        Post(
                             imageUrl!!,
                             binding.caption.editText?.text.toString()
                        )
            Firebase.firestore.collection(POST_NODE).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                    .addOnSuccessListener {
                        Toast.makeText(this@PostActivity, "Post Uploaded", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
            }
                }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
        }
    }
}