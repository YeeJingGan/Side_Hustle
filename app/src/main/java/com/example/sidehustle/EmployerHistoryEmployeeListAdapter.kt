package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerMyJobsHistoryEmployeeListBinding
import com.example.sidehustle.databinding.FragmentEmployerMyJobsOngoingEmployeeListBinding

class EmployerHistoryEmployeeListAdapter(private val allEmployees: List<EmployeeEntity>) :
    RecyclerView.Adapter<EmployerHistoryEmployeeListAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: FragmentEmployerMyJobsHistoryEmployeeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeEntity) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employerHistoryViewEmployeeDetailsButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerHistoryEmployeeDetailsCommentedActivity::class.java
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
        val binding: FragmentEmployerMyJobsHistoryEmployeeListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_employer_my_jobs_history_employee_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}