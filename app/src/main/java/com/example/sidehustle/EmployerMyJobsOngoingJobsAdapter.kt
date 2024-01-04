package com.example.sidehustle

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsOngoingJobsBinding
import androidx.fragment.app.Fragment

class EmployerMyJobsOngoingJobsAdapter(
    private val ongoingJobs: List<EntityJob>,
    private val viewModel: EmployerMyJobsOngoingViewModel,
    private val fragment: Fragment
) :
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
            var adapter = EmployerMyJobsOngoingEmployeesAdapter(emptyList())

            viewModel.ongoingEmployees.observe(fragment.viewLifecycleOwner) {
                adapter = EmployerMyJobsOngoingEmployeesAdapter(it)
                binding.employerOngoingRecyclerview.adapter = adapter
            }

            // TODO : WAGES AND STARS NOT YET GET FROM DATABASE

            binding.employerOngoingRecyclerview.adapter = adapter
            binding.employerOngoingRecyclerview.layoutManager =
                LinearLayoutManager(binding.root.context)
            binding.executePendingBindings()
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