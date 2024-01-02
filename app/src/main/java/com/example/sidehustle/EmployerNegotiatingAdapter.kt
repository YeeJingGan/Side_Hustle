package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerMyJobsNegotiatingBinding


class EmployerNegotiatingAdapter (private val negotiatingJobs: List<JobEntity>):RecyclerView.Adapter<EmployerNegotiatingAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: FragmentEmployerMyJobsNegotiatingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) {
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

            val adapter = EmployerNegotiatingApplicantListAdapter(populateEmployee())
            binding.employerMyJobsNegotiatingRecyclerview.adapter = adapter
            binding.employerMyJobsNegotiatingRecyclerview.layoutManager =
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
        return negotiatingJobs.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(negotiatingJobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentEmployerMyJobsNegotiatingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employer_my_jobs_negotiating,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}