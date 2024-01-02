package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsHistoryEmployeesBinding

class EmployerMyJobsHistoryEmployeesAdapter(private val allEmployees: List<EntityEmployee>) :
    RecyclerView.Adapter<EmployerMyJobsHistoryEmployeesAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ListitemEmployerMyJobsHistoryEmployeesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EntityEmployee) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employerHistoryViewEmployeeDetailsButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerMyJobsHistoryEmployeeDetailsActivity::class.java
//TODO: Commented - Pending Comment Activity Switch
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
        val binding: ListitemEmployerMyJobsHistoryEmployeesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_history_employees,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}