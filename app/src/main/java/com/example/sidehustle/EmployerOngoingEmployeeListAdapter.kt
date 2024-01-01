package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.FragmentEmployerHomeApprovedJobsBinding

class EmployerOngoingEmployeeListAdapter(private val allEmployees: List<EmployerOngoingEmployeeListData>):
    RecyclerView.Adapter<EmployerOngoingEmployeeListAdapter.ViewHolder> (),Filterable{

    private var employees: List<EmployerOngoingEmployeeListData> = allEmployees

    inner class ViewHolder(private val binding: Fragment)

//    inner class ViewHolder(private val binding: FragmentEmployerHomeApprovedJobsBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(job: JobEntity) {
//            binding.job = job
//            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY
//            binding.employerHomeApprovedJobsViewDetailsButton.setOnClickListener {
//                val intent = Intent(it.context, EmployerHomeJobDetailsActivity::class.java)
//                intent.putExtra("jobID", job.jobID)
//                it.context.startActivity(intent)
//            }
//            binding.executePendingBindings()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return filteredJobs.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(filteredJobs[position])
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding: FragmentEmployerHomeApprovedJobsBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_employer_home_approved_jobs,
//            parent,
//            false
//        )
//        return ViewHolder(binding)
//    }
}