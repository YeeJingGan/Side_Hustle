package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsNegotiatingJobsBinding


class EmployerMyJobsNegotiatingJobsAdapter (private val negotiatingJobs: List<EntityJob>):RecyclerView.Adapter<EmployerMyJobsNegotiatingJobsAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: ListitemEmployerMyJobsNegotiatingJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: EntityJob) {
            binding.job = job
            binding.employerNegotiatingExpandButton.setOnClickListener {
                val recyclerView = binding.employerMyJobsNegotiatingRecyclerview
                if (recyclerView.visibility == View.VISIBLE) {
                    recyclerView.visibility = View.GONE
                    binding.employerNegotiatingExpandButton.setImageResource(R.drawable.ic_expand)
                } else {
                    recyclerView.visibility = View.VISIBLE
                    binding.employerNegotiatingExpandButton.setImageResource(R.drawable.ic_expand_less)
                }
            }

            val adapter = EmployerMyJobsNegotiatingApplicantsAdapter(populateEmployee())
            binding.employerMyJobsNegotiatingRecyclerview.adapter = adapter
            binding.employerMyJobsNegotiatingRecyclerview.layoutManager =
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
        return negotiatingJobs.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(negotiatingJobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployerMyJobsNegotiatingJobsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_negotiating_jobs,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}