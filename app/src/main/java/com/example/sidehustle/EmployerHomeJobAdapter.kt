package com.example.sidehustle

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerHomeJobsBinding
import kotlinx.coroutines.launch
import java.util.Locale

class EmployerHomeJobAdapter(
    private val jobs: List<EntityJob>,
    private val viewModel: EmployerHomeViewModel,
) :
    RecyclerView.Adapter<EmployerHomeJobAdapter.ViewHolder>(), Filterable {

    private var filteredJobs: List<EntityJob> = jobs

    inner class ViewHolder(private val binding: ListitemEmployerHomeJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: EntityJob) {
            binding.job = job
            viewModel.viewModelScope.launch {
                binding.applicantCount = viewModel.getApplicantCount(job.jobID)
            }
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employerHomeApprovedJobsViewDetailsButton.setOnClickListener {
                val intent = Intent(it.context, EmployerHomeJobDetailsActivity::class.java)
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
        val binding: ListitemEmployerHomeJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_home_jobs,
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
                        if (job.title.lowercase(Locale.ROOT)
                                .contains(filterPattern) || job.state.lowercase(Locale.ROOT)
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