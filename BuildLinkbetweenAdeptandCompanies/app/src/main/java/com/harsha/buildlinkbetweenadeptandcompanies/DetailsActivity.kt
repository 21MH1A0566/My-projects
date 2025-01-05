package com.harsha.buildlinkbetweenadeptandcompanies

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.compose.material3.DatePickerDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Details
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityDetailsBinding
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivitySignupBinding
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityUserBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.Month
import java.util.Calendar
import java.util.Locale

class DetailsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    lateinit var user: Details
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = Details()
        setContentView(binding.root)
        val items = listOf("Student", "Graduate", "Employee", "Company", "Developer", "Manager")
        val items1 = listOf("B.E/B.tech", "PhD", "Masters", "Degree")
        val items2 = listOf("1", "2", "3", "4", "Completed")
        val items3 = listOf(
            "Computer Science and Engineering(CSE)",
            "Electronics and Communication Engineering(ECE)",
            "Electrical and Electronics Engineering(EEE)",
            "Others"
        )
        val items4 = listOf(
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
        val autoComplete: AutoCompleteTextView = findViewById(R.id.occupation)
        val adapter = ArrayAdapter(this@DetailsActivity, R.layout.list_item, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete2: AutoCompleteTextView = findViewById(R.id.highestdegree)
        val adapter2 = ArrayAdapter(this@DetailsActivity, R.layout.list_item, items1)
        autoComplete2.setAdapter(adapter2)
        autoComplete2.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete3: AutoCompleteTextView = findViewById(R.id.years)
        val adapter3 = ArrayAdapter(this@DetailsActivity, R.layout.list_item, items2)
        autoComplete3.setAdapter(adapter3)
        autoComplete3.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete4: AutoCompleteTextView = findViewById(R.id.specialization)
        val adapter4 = ArrayAdapter(this@DetailsActivity, R.layout.list_item, items3)
        autoComplete4.setAdapter(adapter4)
        autoComplete4.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val adapter5=ArrayAdapter(this@DetailsActivity,R.layout.list_item,items4)
        binding.languages.setAdapter(adapter5)
        binding.languages.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        binding.nextbutton.setOnClickListener {
            if (binding.firstname.editText?.text.toString()
                    .equals("") or binding.lastname.editText?.text.toString()
                    .equals("") or binding.occupation.text.equals("") or binding.bio.editText?.text.toString()
                    .equals("") or binding.phone.equals("") or binding.highestdegree.text.equals("") or binding.years.text.equals(
                    ""
                ) or binding.specialization.text.equals("") or binding.languages.text.equals("")
            ) {
                Toast.makeText(
                    this@DetailsActivity,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().updateCurrentUser(Firebase.auth.currentUser!!)
                    .addOnCompleteListener { result1 ->
                        if (result1.isSuccessful) {
                            user.firstname = binding.firstname.editText?.text.toString()
                            user.lastname = binding.lastname.editText?.text.toString()
                            user.occupation = binding.occupation.text.toString()
                            user.bio = binding.bio.editText?.text.toString()
                            user.phone = binding.phone.editText?.text.toString()
                            user.dob = binding.dob.editText?.text.toString()
                            user.highestdegree = binding.highestdegree.text.toString()
                            user.years = binding.years.text.toString()
                            user.specialization = binding.specialization.text.toString()
                            user.languages=binding.languages.text.toString()
                            Firebase.firestore.collection("Details")
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@DetailsActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        } else {
                            Toast.makeText(
                                this@DetailsActivity,
                                result1.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}