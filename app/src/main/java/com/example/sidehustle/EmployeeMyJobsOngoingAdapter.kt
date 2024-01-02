package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployeeMyJobsOngoingFragmentBinding

class EmployeeMyJobsOngoingAdapter(private val allJobs: List<EntityJob>) :
    RecyclerView.Adapter<EmployeeMyJobsOngoingAdapter.ViewHolder>(){

    private var jobs: List<EntityJob> = allJobs

    inner class ViewHolder(private val binding: ListitemEmployeeMyJobsOngoingFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: EntityJob) {
            binding.job = job
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employeeOngoingButton.setOnClickListener {
                val intent = Intent(it.context, EmployeeMyJobsOngoingJobDetailsActivity::class.java)
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
        val binding: ListitemEmployeeMyJobsOngoingFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employee_my_jobs_ongoing_fragment,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}