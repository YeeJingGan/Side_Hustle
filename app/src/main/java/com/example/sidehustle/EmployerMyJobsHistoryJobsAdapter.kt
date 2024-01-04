package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsHistoryJobsBinding

class EmployerMyJobsHistoryJobsAdapter(
    private val historyJobs: List<EntityJob>,
    private val viewModel: EmployerMyJobsHistoryViewModel,
    private val fragment: Fragment
) :
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

//            var adapter = EmployerMyJobsNegotiatingApplicantsAdapter(emptyList(),viewModel)
//
//            viewModel.historyEmployees.observe(fragment.viewLifecycleOwner) {
//                adapter = EmployerMyJobsNegotiatingApplicantsAdapter(it, viewModel)
//                binding.employerMyJobsHistoryRecyclerview.adapter = adapter
//            }
//
//            // TODO : WAGES AND STARS NOT YET GET FROM DATABASE
//            binding.employerMyJobsHistoryRecyclerview.adapter = adapter
//            binding.employerMyJobsHistoryRecyclerview.layoutManager =
//                LinearLayoutManager(binding.root.context)
            binding.executePendingBindings()
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