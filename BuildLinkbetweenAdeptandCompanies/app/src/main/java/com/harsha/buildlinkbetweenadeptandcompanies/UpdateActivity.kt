package com.harsha.buildlinkbetweenadeptandcompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Details
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityDetailsBinding
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.ActivityUpdateBinding
import com.harsha.buildlinkbetweenadeptandcompanies.fragments.ProfileFragment
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_DETAILS
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_PROFILE_FOLDER
import com.harsha.buildlinkbetweenadeptandcompanies.utils.uploadImage
import com.squareup.picasso.Picasso

class UpdateActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }
    lateinit var user: Details
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        user = Details()
        Firebase.firestore.collection(USER_DETAILS).document(Firebase.auth.currentUser!!.uid)
            .get().addOnSuccessListener {
                user = it.toObject<Details>()!!
                binding.changeFirstname.editText?.setText(user.firstname)
                binding.changeLastname.editText?.setText(user.lastname)
                binding.changeOccupation.setText(user.occupation)
                binding.changePhone.editText?.setText(user.phone)
                binding.changeDob.editText?.setText(user.dob)
                binding.changeBio.editText?.setText(user.bio)
                binding.changeHighestdegree.setText(user.highestdegree)
                binding.changeYears.setText(user.years)
                binding.changeSpecialization.setText(user.specialization)
                binding.changeLanguages.setText(user.languages)
            }
        val checkBox1 = findViewById<CheckBox>(R.id.confirm_profile) as CheckBox
        val items = listOf("Student", "Graduate", "Employee", "Company","Developer","Manager")
        val items1 = listOf("B.E/B.tech", "PhD", "Masters", "Degree")
        val items2 = listOf("1", "2", "3", "4","Completed")
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
        val autoComplete: AutoCompleteTextView = findViewById(R.id.change_occupation)
        val adapter = ArrayAdapter(this@UpdateActivity, R.layout.list_item, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete2: AutoCompleteTextView = findViewById(R.id.change_highestdegree)
        val adapter2 = ArrayAdapter(this@UpdateActivity, R.layout.list_item, items1)
        autoComplete2.setAdapter(adapter2)
        autoComplete2.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete3: AutoCompleteTextView = findViewById(R.id.change_years)
        val adapter3 = ArrayAdapter(this@UpdateActivity, R.layout.list_item, items2)
        autoComplete3.setAdapter(adapter3)
        autoComplete3.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val autoComplete4: AutoCompleteTextView = findViewById(R.id.change_specialization)
        val adapter4 = ArrayAdapter(this@UpdateActivity, R.layout.list_item, items3)
        autoComplete4.setAdapter(adapter4)
        autoComplete4.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
            }
        val adapter5=ArrayAdapter(this@UpdateActivity,R.layout.list_item,items4)
        binding.changeLanguages.setAdapter(adapter5)
        binding.changeLanguages.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        binding.updateProfileAndPasswordButton.setOnClickListener {
            val intent = Intent(this@UpdateActivity, signup_activity::class.java)
            intent.putExtra("MODE", 1)
            this@UpdateActivity?.startActivity(intent)
            this@UpdateActivity?.finish()
        }
        binding.updateProfileButton.setOnClickListener {
            if (binding.changeFirstname.editText?.text.toString()
                    .equals("") or binding.changeLastname.editText?.text.toString()
                    .equals("") or binding.changeOccupation.text.toString()
                    .equals("") or binding.changePhone.editText?.text.toString()
                    .equals("") or binding.changeDob.editText?.text.toString()
                    .equals("") or binding.changeBio.editText?.text.toString()
                    .equals("") or binding.changeHighestdegree.text.toString()
                    .equals("") or binding.changeYears.text.toString()
                    .equals("") or binding.changeSpecialization.text.equals("") or binding.changeLanguages.text.equals("")
            ) {
                Toast.makeText(
                    this@UpdateActivity,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!checkBox1.isChecked) {
                Toast.makeText(this@UpdateActivity, "Please mark the CheckBox", Toast.LENGTH_SHORT)
                    .show()
            } else {
                FirebaseAuth.getInstance().updateCurrentUser(Firebase.auth.currentUser!!)
                    .addOnCompleteListener { result1 ->
                        if (result1.isSuccessful) {
                            user.firstname = binding.changeFirstname.editText?.text.toString()
                            user.lastname = binding.changeLastname.editText?.text.toString()
                            user.occupation = binding.changeOccupation.text.toString()
                            user.phone = binding.changePhone.editText?.text.toString()
                            user.dob = binding.changeDob.editText?.text.toString()
                            user.bio = binding.changeBio.editText?.text.toString()
                            user.highestdegree = binding.changeHighestdegree.text.toString()
                            user.years = binding.changeYears.text.toString()
                            user.specialization=binding.changeSpecialization.text.toString()
                            user.languages=binding.changeLanguages.text.toString()
                            Firebase.firestore.collection("Details")
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@UpdateActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        } else {
                            Toast.makeText(
                                this@UpdateActivity,
                                result1.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}