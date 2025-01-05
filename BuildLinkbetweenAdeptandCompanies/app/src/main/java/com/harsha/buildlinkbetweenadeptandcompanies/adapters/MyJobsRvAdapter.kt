package com.harsha.buildlinkbetweenadeptandcompanies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Jobs
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.MyJobsRvDesignBinding

class MyJobsRvAdapter(
    var context: Context,
    var jobtitle: ArrayList<Jobs>,
    var experience: ArrayList<Jobs>,
    var skills: ArrayList<Jobs>,
    var jobstatus: ArrayList<Jobs>,
    var salary: ArrayList<Jobs>,
    var aboutjob: ArrayList<Jobs>
) :
    RecyclerView.Adapter<MyJobsRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: MyJobsRvDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyJobsRvDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jobtitle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.jobTitle.text=jobtitle.get(position).jobtitle
        holder.binding.experienceWanted.text=experience.get(position).experience
        holder.binding.skillsWanted.text=skills.get(position).skills
        holder.binding.jobStatusWanted.text=jobstatus.get(position).jobstatus
        holder.binding.salaryWanted.text=salary.get(position).salary
        holder.binding.aboutJobWanted.text=aboutjob.get(position).aboutjob
    }
}