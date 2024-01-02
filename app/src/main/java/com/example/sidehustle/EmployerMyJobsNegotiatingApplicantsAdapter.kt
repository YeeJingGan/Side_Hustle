package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsNegotiatingApplicantsBinding


class EmployerMyJobsNegotiatingApplicantsAdapter(private val allEmployees: List<EntityEmployee>) :
    RecyclerView.Adapter<EmployerMyJobsNegotiatingApplicantsAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ListitemEmployerMyJobsNegotiatingApplicantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EntityEmployee) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.applicantsListViewApplicantButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerMyJobsNegotiatingApplicantDetailsActivity::class.java
                )
                intent.putExtra("employeeID", employee.employeeID)
                it.context.startActivity(intent)
            }
            binding.executePendingBindings()
        }
    }


    override fun getItemCount(): Int {
        return allEmployees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allEmployees[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployerMyJobsNegotiatingApplicantsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_negotiating_applicants,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}