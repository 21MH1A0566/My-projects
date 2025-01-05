package com.harsha.buildlinkbetweenadeptandcompanies.post

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.HomeActivity
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Details
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Jobs
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityJobsBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_DETAILS
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_JOBS
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE

class JobsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityJobsBinding.inflate(layoutInflater)
    }
    lateinit var user: Jobs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = Jobs()
        var image66:String?
        var name66: String?
        var occupation66: String?
        val items = listOf("No need", "2-3 years", "5-6 years", "8-9 years")
        val items1 = listOf(
            "Python",
            "C",
            "C++",
            "Java",
            "C#",
            "Visual Basic .NET",
            "JavaScript",
            "SQL",
            "Assembly language",
            "PHP",
            "R",
            "Go",
            "Classic Visual Basic",
            "MATLAB",
            "Swift",
            "Delphi/Object Pascal",
            "Ruby",
            "Perl",
            "Objective-C",
            "Rust",
            "Scratch",
            "SAS",
            "Kotlin",
            "Julia",
            "Lua",
            "Fortran",
            "COBOL",
            "Lisp",
            "(Visual) FoxPro",
            "Ada",
            "Dart",
            "Scala",
            "Prolog",
            "D",
            "PL/SQL",
            "Bash",
            "Powershell",
            "Haskell",
            "Logo",
            "Transact SQL",
            "XML"
        )
        val checkBox1 = findViewById<CheckBox>(R.id.confirm_job) as CheckBox
        val items2 = listOf("Part - Time", "Full - Time")
        val autoComplete: AutoCompleteTextView = findViewById(R.id.experience)
        val adapter = ArrayAdapter(this@JobsActivity, R.layout.list_item, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete1: AutoCompleteTextView = findViewById(R.id.job_status)
        val adapter1 = ArrayAdapter(this@JobsActivity, R.layout.list_item, items2)
        autoComplete1.setAdapter(adapter1)
        autoComplete1.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val adapter2 = ArrayAdapter(this@JobsActivity, R.layout.list_item, items1)
        binding.skills.setAdapter(adapter2)
        binding.skills.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        setSupportActionBar(binding.materialToolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar2.setNavigationOnClickListener {
            startActivity(Intent(this@JobsActivity, HomeActivity::class.java))
        }
        binding.cancelButton1.setOnClickListener {
            startActivity(Intent(this@JobsActivity, HomeActivity::class.java))
        }

        binding.postButton1.setOnClickListener {
            if (binding.jobTitle.editText?.text.toString()
                    .equals("") or binding.experience.text.toString()
                    .equals("") or binding.skills.text.toString()
                    .equals("") or binding.jobStatus.text.toString()
                    .equals("") or binding.salary.editText?.text.toString()
                    .equals("") or binding.aboutJob.editText?.text.toString().equals("")
            ) {
                Toast.makeText(
                    this@JobsActivity,
                    "Please fill all the requirements",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!checkBox1.isChecked) {
                Toast.makeText(
                    this@JobsActivity,
                    "Please mark the checkbox",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().updateCurrentUser(Firebase.auth.currentUser!!)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                                    Firebase.firestore.collection(USER_DETAILS)
                                        .document(Firebase.auth.currentUser!!.uid).get()
                                        .addOnSuccessListener { it1 ->
                                            var user: Details = it1.toObject<Details>()!!
                                            occupation66 = user.occupation.toString()
                                            name66 = user.firstname.toString()
                                            Firebase.firestore.collection(USER_NODE)
                                                .document(Firebase.auth.currentUser!!.uid).get()
                                                .addOnSuccessListener {
                                                    var user: User = it.toObject<User>()!!
                                                    image66 = user.image!!.toString()
                                                    val jobs: Jobs = Jobs(
                                                        binding.jobTitle.editText?.text.toString(),
                                                        binding.experience.text.toString(),
                                                        binding.skills.text.toString(),
                                                        binding.jobStatus.text.toString(),
                                                        binding.salary.editText?.text.toString(),
                                                        binding.aboutJob.editText?.text.toString(),
                                                        image66,
                                                        name66,
                                                        occupation66
                                                    )
                                                    Firebase.firestore.collection(USER_JOBS)
                                                        .document()
                                                        .set(jobs)
                                                        .addOnSuccessListener {
                                                            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + USER_JOBS)
                                                                .document().set(jobs)
                                                            Toast.makeText(
                                                                this@JobsActivity,
                                                                "Job Uploaded.",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                            startActivity(
                                                                Intent(
                                                                    this@JobsActivity,
                                                                    HomeActivity::class.java
                                                                )
                                                            )
                                                            finish()
                                                        }
                                                }
                                        }
                        } else {
                            Toast.makeText(
                                this@JobsActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}