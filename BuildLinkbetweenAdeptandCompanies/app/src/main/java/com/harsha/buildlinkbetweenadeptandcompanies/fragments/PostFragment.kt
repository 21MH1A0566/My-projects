package com.harsha.buildlinkbetweenadeptandcompanies.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.FragmentPostBinding
import com.harsha.buildlinkbetweenadeptandcompanies.post.JobsActivity
import com.harsha.buildlinkbetweenadeptandcompanies.post.PostActivity

class PostFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPostBinding.inflate(inflater, container, false)
        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
        }
        binding.job.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),JobsActivity::class.java))
        }
        return binding.root
    }

    companion object {

            }
    }