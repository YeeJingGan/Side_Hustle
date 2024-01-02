package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerMyJobsHistoryBinding
import com.example.sidehustle.databinding.FragmentEmployerMyJobsOngoingBinding

class EmployerHistoryAdapter(private val historyJobs: List<JobEntity>) :
    RecyclerView.Adapter<EmployerHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FragmentEmployerMyJobsHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) {
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

            val adapter = EmployerHistoryEmployeeListAdapter(populateEmployee())
            binding.employerMyJobsHistoryRecyclerview.adapter = adapter
            binding.employerMyJobsHistoryRecyclerview.layoutManager =
                LinearLayoutManager(binding.root.context)
            binding.executePendingBindings()
        }

        private fun populateEmployee(): List<EmployeeEntity> {
            return listOf(
                EmployeeEntity(
                    1,
                    "Gan Yee Jing",
                    "gyjemployee@email.com",
                    "abc123",
                    byteArrayOf(0x48, 101, 108, 108, 111)
                ),
                EmployeeEntity(
                    2,
                    "Yeap Jie Shen",
                    "yjsemployee@email.com",
                    "abc123",
                    byteArrayOf(0x48, 101, 108, 108, 111)
                ),
                EmployeeEntity(
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
        val binding: FragmentEmployerMyJobsHistoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employer_my_jobs_history,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}