package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployeeHistoryBinding
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsHistoryBinding
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsOngoingBinding

class EmployeeHistoryAdapter(private val allJobs: List<JobEntity>) :
    RecyclerView.Adapter<EmployeeHistoryAdapter.ViewHolder>(){

    private var jobs: List<JobEntity> = allJobs

    inner class ViewHolder(private val binding: FragmentEmployeeMyJobsHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobEntity) {
            binding.job = job
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.historyDetailButton.setOnClickListener {
                val intent = Intent(it.context, EmployeeMyJobsHistoryJobDetailsActivity::class.java)
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
        val binding: FragmentEmployeeMyJobsHistoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employee_my_jobs_history,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}