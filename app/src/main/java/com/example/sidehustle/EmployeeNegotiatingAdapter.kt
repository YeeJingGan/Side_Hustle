package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsNegotiatingBinding
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsOngoingBinding

class EmployeeNegotiatingAdapter(private val allJobs: List<JobEntity>) :
    RecyclerView.Adapter<EmployeeNegotiatingAdapter.ViewHolder>(){

    private var jobs: List<JobEntity> = allJobs

    inner class ViewHolder(private val binding: FragmentEmployeeMyJobsNegotiatingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobEntity) {
            binding.job = job
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employeeNegotiatingButton.setOnClickListener {
                //TODO: Switch Waiting Reply / Reply
                val intent = Intent(it.context, EmployeeMyJobsNegotiatingJobDetailsActivity::class.java)
                intent.putExtra("jobID", job.jobID)
                it.context.startActivity(intent)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentEmployeeMyJobsNegotiatingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employee_my_jobs_negotiating,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}