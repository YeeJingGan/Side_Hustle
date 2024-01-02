package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsOngoingJobsBinding

class EmployerMyJobsOngoingJobsAdapter(private val ongoingJobs: List<EntityJob>) :
    RecyclerView.Adapter<EmployerMyJobsOngoingJobsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListitemEmployerMyJobsOngoingJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: EntityJob) {
            binding.job = job
            binding.employerOngoingExpandButton.setOnClickListener {
                val recyclerView = binding.employerOngoingRecyclerview
                if (recyclerView.visibility == View.VISIBLE) {
                    recyclerView.visibility = View.GONE
                    binding.employerOngoingExpandButton.setImageResource(R.drawable.ic_expand)
                } else {
                    recyclerView.visibility = View.VISIBLE
                    binding.employerOngoingExpandButton.setImageResource(R.drawable.ic_expand_less)
                }
            }

            val adapter = EmployerMyJobsOngoingEmployeesAdapter(populateEmployee())
            binding.employerOngoingRecyclerview.adapter = adapter
            binding.employerOngoingRecyclerview.layoutManager =
                LinearLayoutManager(binding.root.context)
            binding.executePendingBindings()
        }

        private fun populateEmployee(): List<EntityEmployee> {
            return listOf(
                EntityEmployee(
                    1,
                    "Gan Yee Jing",
                    "gyjemployee@email.com",
                    "abc123",
                    byteArrayOf(0x48, 101, 108, 108, 111)
                ),
                EntityEmployee(
                    2,
                    "Yeap Jie Shen",
                    "yjsemployee@email.com",
                    "abc123",
                    byteArrayOf(0x48, 101, 108, 108, 111)
                ),
                EntityEmployee(
                    3,
                    "Jerome Subash",
                    "jeromeemployee@email.com",
                    "abc123",
                    byteArrayOf(0x48, 101, 108, 108, 111)
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return ongoingJobs.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(ongoingJobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployerMyJobsOngoingJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_ongoing_jobs,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}