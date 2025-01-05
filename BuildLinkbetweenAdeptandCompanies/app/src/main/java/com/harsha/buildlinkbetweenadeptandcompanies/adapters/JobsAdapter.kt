package com.harsha.buildlinkbetweenadeptandcompanies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Jobs
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.JobsDgBinding
import com.squareup.picasso.Picasso

class JobsAdapter(
    var context: Context, var jobtitle: ArrayList<Jobs>,
    var experience: ArrayList<Jobs>,
    var skills: ArrayList<Jobs>,
    var jobstatus: ArrayList<Jobs>,
    var salary: ArrayList<Jobs>,
    var aboutjob: ArrayList<Jobs>,
) : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: JobsDgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = JobsDgBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jobtitle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(jobtitle.get(position).profileLink).placeholder(R.drawable.profiling).into(holder.binding.profileImage1)
        holder.binding.name1.text=jobtitle.get(position).nameLink
        holder.binding.occupation1.text=jobstatus.get(position).occupationLink
        holder.binding.jobTitle1.text = jobtitle.get(position).jobtitle
        holder.binding.experienceWanted1.text = experience.get(position).experience
        holder.binding.skillsWanted1.text = skills.get(position).skills
        holder.binding.jobStatusWanted1.text = jobstatus.get(position).jobstatus
        holder.binding.salaryWanted1.text = salary.get(position).salary
        holder.binding.aboutJobWanted1.text = aboutjob.get(position).aboutjob
    }
}