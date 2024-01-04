package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsOngoingEmployeesBinding

class EmployerMyJobsOngoingEmployeesAdapter(private val allEmployees: List<EntityEmployee>) :
    RecyclerView.Adapter<EmployerMyJobsOngoingEmployeesAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ListitemEmployerMyJobsOngoingEmployeesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EntityEmployee) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
            binding.employerMyJobsOngoingEmployeeListLisitemButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerMyJobsOngoingEmployeeDetailsActivity::class.java
                )
                intent.putExtra("employeeID", employee.employeeID)
                it.context.startActivity(intent)
            }
            updateStarColors(5)
            binding.executePendingBindings()
        }
        private fun updateStarColors(starsCount: Int) {
            val stars = arrayOf(
                binding.employerMyJobsOngoingEmployeeListLisitemStar1,
                binding.employerMyJobsOngoingEmployeeListLisitemStar2,
                binding.employerMyJobsOngoingEmployeeListLisitemStar3,
                binding.employerMyJobsOngoingEmployeeListLisitemStar4,
                binding.employerMyJobsOngoingEmployeeListLisitemStar5
            )

            for (i in 0..<stars.size) {
                if (i < starsCount) {
                    stars[i].setImageResource(R.drawable.ic_star_24px)
                    stars[i].setColorFilter(Color.parseColor("#FDB915"))
                } else {
                    stars[i].setImageResource(R.drawable.ic_star_hollow_24px)
                    stars[i].setColorFilter(Color.parseColor("#000000"))
                }
            }
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
        val binding:  ListitemEmployerMyJobsOngoingEmployeesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_ongoing_employees,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}
