package com.example.sidehustle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerHomeApprovedJobsBinding

class EmployerHomeJobAdapter(private val jobs: List<JobEntity>) :
    RecyclerView.Adapter<EmployerHomeJobAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FragmentEmployerHomeApprovedJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobEntity) {
            binding.job = job
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
        val binding: FragmentEmployerHomeApprovedJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employer_home_approved_jobs,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}