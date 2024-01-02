package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerHomeApprovedJobsBinding
import com.example.sidehustle.databinding.FragmentEmployerMyJobsOngoingEmployeeListBinding

class EmployerOngoingEmployeeListAdapter(private val allEmployees: List<EmployeeEntity>) :
    RecyclerView.Adapter<EmployerOngoingEmployeeListAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: FragmentEmployerMyJobsOngoingEmployeeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeEntity) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employerMyJobsOngoingEmployeeListLisitemButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerMyJobsOngoingEmployeeListEmployeeDetailsActivity::class.java
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
        val binding:  FragmentEmployerMyJobsOngoingEmployeeListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employer_my_jobs_ongoing_employee_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}
