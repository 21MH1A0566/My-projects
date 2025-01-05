package com.harsha.buildlinkbetweenadeptandcompanies.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.DetailsActivity
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Details
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.UpdateActivity
import com.harsha.buildlinkbetweenadeptandcompanies.adapters.ViewPagerAdapter
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.FragmentProfileBinding
import com.harsha.buildlinkbetweenadeptandcompanies.signup_activity
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_DETAILS
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

            binding.editProfile.setOnClickListener {
            val intent=Intent(activity,UpdateActivity::class.java)
            activity?.startActivity(intent)
        }
        viewPagerAdapter=ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragments(MyPostFragment(),"My Posts")
        viewPagerAdapter.addFragments(MyJobsFragment(),"My Jobs")
        binding.viewPager.adapter=viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

    companion object {

    }
    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user: User = it.toObject<User>()!!
                binding.name1.text = user.name
                if (!user.image.isNullOrEmpty()) {
                    Picasso.get().load(user.image).into(binding.profileImage)
                }
            }
        Firebase.firestore.collection(USER_DETAILS).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener{
                val user:Details=it.toObject<Details>()!!
                binding.bio1.text=user.bio
                binding.occupation1.text=user.occupation
            }
    }
}