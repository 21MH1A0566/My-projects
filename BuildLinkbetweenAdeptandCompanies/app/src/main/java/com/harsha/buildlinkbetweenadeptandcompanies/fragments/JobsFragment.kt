package com.harsha.buildlinkbetweenadeptandcompanies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Jobs
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.adapters.JobsAdapter
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.FragmentJobsBinding
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.MyJobsRvDesignBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_JOBS

class JobsFragment : Fragment() {
    private lateinit var binding: FragmentJobsBinding
    lateinit var adapter:JobsAdapter
    var jobtitle=ArrayList<Jobs>()
    var experience=ArrayList<Jobs>()
    var skills=ArrayList<Jobs>()
    var jobstatus=ArrayList<Jobs>()
    var salary=ArrayList<Jobs>()
    var aboutjob=ArrayList<Jobs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentJobsBinding.inflate(inflater, container, false)
        adapter= JobsAdapter(requireContext(),jobtitle, experience,skills, jobstatus, salary, aboutjob,)
        binding.viewJobs.adapter=adapter
        Firebase.firestore.collection(USER_JOBS).get().addOnSuccessListener {
            var  tempTitleList= arrayListOf<Jobs>()
            var tempExperienceList= arrayListOf<Jobs>()
            var tempSkillsList= arrayListOf<Jobs>()
            var tempStatusList= arrayListOf<Jobs>()
            var tempSalaryList= arrayListOf<Jobs>()
            var tempAboutList= arrayListOf<Jobs>()
            jobtitle.clear()
            experience.clear()
            skills.clear()
            jobstatus.clear()
            salary.clear()
            aboutjob.clear()
            for(i in it.documents)
            {
                var title:Jobs=i.toObject<Jobs>()!!
                var experience:Jobs=i.toObject<Jobs>()!!
                var skills:Jobs=i.toObject<Jobs>()!!
                var jobstatus:Jobs=i.toObject<Jobs>()!!
                var salary:Jobs=i.toObject<Jobs>()!!
                var aboutjob:Jobs=i.toObject<Jobs>()!!
                tempTitleList.add(title)
                tempExperienceList.add(experience)
                tempSkillsList.add(skills)
                tempStatusList.add(jobstatus)
                tempSalaryList.add(salary)
                tempAboutList.add(aboutjob)
            }
            jobtitle.addAll(tempTitleList)
            experience.addAll(tempExperienceList)
            skills.addAll(tempSkillsList)
            jobstatus.addAll(tempStatusList)
            salary.addAll(tempSalaryList)
            aboutjob.addAll(tempAboutList)
            jobtitle.reverse()
            experience.reverse()
            skills.reverse()
            jobstatus.reverse()
            salary.reverse()
            aboutjob.reverse()
            adapter.notifyDataSetChanged()

        }
        return binding.root
    }

    companion object {
    }
}