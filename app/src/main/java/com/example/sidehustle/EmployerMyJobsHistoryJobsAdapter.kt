package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsHistoryJobsBinding

class EmployerMyJobsHistoryJobsAdapter(private val historyJobs: List<EntityJob>) :
    RecyclerView.Adapter<EmployerMyJobsHistoryJobsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListitemEmployerMyJobsHistoryJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: EntityJob) {
            binding.job = job
            binding.employerMyJobsHistoryExpandButton.setOnClickListener {
                val recyclerView = binding.employerMyJobsHistoryRecyclerview
                if (recyclerView.visibility == View.VISIBLE) {
                    recyclerView.visibility = View.GONE
                    binding.employerMyJobsHistoryExpandButton.setImageResource(R.drawable.ic_expand)
                } else {
                    recyclerView.visibility = View.VISIBLE
                    binding.employerMyJobsHistoryExpandButton.setImageResource(R.drawable.ic_expand_less)
                }
            }

            val adapter = EmployerMyJobsHistoryEmployeesAdapter(populateEmployee())
            binding.employerMyJobsHistoryRecyclerview.adapter = adapter
            binding.employerMyJobsHistoryRecyclerview.layoutManager =
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
        return historyJobs.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(historyJobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployerMyJobsHistoryJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_history_jobs,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}