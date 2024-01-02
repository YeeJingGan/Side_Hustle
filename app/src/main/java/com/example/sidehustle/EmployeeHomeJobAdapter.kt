package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployeeHomeJobsBinding
import java.util.Locale

class EmployeeHomeJobAdapter(private val jobs: List<EntityJob>) :
    RecyclerView.Adapter<EmployeeHomeJobAdapter.ViewHolder>(), Filterable {

    private var filteredJobs: List<EntityJob> = jobs

    inner class ViewHolder(private val binding: ListitemEmployeeHomeJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: EntityJob) {
            binding.job = job
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employeeHomeJobsDetailsButton.setOnClickListener {
                val intent = Intent(it.context, EmployeeHomeJobDetailsActivity::class.java)
                intent.putExtra("jobID", job.jobID)
                it.context.startActivity(intent)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return filteredJobs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredJobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployeeHomeJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employee_home_jobs,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<EntityJob>()

                if (constraint.isNullOrBlank()) {
                    filteredList.addAll(jobs)
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()

                    for (job in jobs) {
                        if (job.jobName.lowercase(Locale.ROOT)
                                .contains(filterPattern) || job.jobState.lowercase(Locale.ROOT)
                                .contains(filterPattern)
                        ) {
                            filteredList.add(job)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredJobs = results?.values as List<EntityJob>
                notifyDataSetChanged()
            }

        }
    }
}