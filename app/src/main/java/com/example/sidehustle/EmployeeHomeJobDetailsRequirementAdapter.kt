package com.example.sidehustle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployeeHomeJobDetailsRequirementsBinding

class EmployeeHomeJobDetailsRequirementAdapter(private val requirements: List<String>) :
    RecyclerView.Adapter<EmployeeHomeJobDetailsRequirementAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ListitemEmployeeHomeJobDetailsRequirementsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(requirement : String){
            binding.employeeHomeJobDetailsRequirementItemTextview.text = requirement
        }
    }

    override fun getItemCount(): Int {
        return requirements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(requirements[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ListitemEmployeeHomeJobDetailsRequirementsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employee_home_job_details_requirements,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}