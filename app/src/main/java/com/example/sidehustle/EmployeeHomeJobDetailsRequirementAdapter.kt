package com.example.sidehustle

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.EmployeeHomeJobDetailsRequirementItemBinding

class EmployeeHomeJobDetailsRequirementAdapter(private val requirements: List<String>) :
    RecyclerView.Adapter<EmployeeHomeJobDetailsRequirementAdapter.ViewHolder>() {
    class ViewHolder(private val binding: EmployeeHomeJobDetailsRequirementItemBinding) :
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

        val binding: EmployeeHomeJobDetailsRequirementItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.employee_home_job_details_requirement_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}