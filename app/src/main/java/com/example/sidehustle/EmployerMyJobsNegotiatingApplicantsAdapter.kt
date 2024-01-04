package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsNegotiatingApplicantsBinding
import kotlinx.coroutines.launch


class EmployerMyJobsNegotiatingApplicantsAdapter(
    private val allEmployees: List<EntityEmployee>,
    private val viewModel: EmployerMyJobsNegotiatingViewModel,
    private val job : EntityJob
) :
    RecyclerView.Adapter<EmployerMyJobsNegotiatingApplicantsAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ListitemEmployerMyJobsNegotiatingApplicantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EntityEmployee) {
            binding.employee = employee
            // TODO : PUT EXTRA PASS DATA TO DESTINATION ACTIVITY

            var starCount = 0
            viewModel.viewModelScope.launch {
                starCount = viewModel.getAverageRatingByEmployeeIDAndCommenter(employee.employeeID,"EMPLOYER").toInt()
                updateStarColors(starCount)
                val latestNegotiation = viewModel.getLastestNegotiationByEmployeeIDAndJobID(employee.employeeID,job.jobID)

                if(latestNegotiation.negotiator == "EMPLOYER"){
                    binding.offerStatus.text = "Offer Sent"
                }else{
                    binding.offerStatus.text = "Pending Review"
                }

                binding.applicantsWagesOffered.text = "RM ${latestNegotiation.pay}"
            }
            binding.applicantsListViewApplicantButton.setOnClickListener {
                val intent = Intent(
                    it.context,
                    EmployerMyJobsNegotiatingApplicantDetailsActivity::class.java
                )
                intent.putExtra("employeeID", employee.employeeID)
                intent.putExtra("jobID",job.jobID)
                intent.putExtra("starCount",starCount)
                it.context.startActivity(intent)
            }
            binding.executePendingBindings()
        }

        private fun updateStarColors(starsCount: Int) {
            val stars = arrayOf(
                binding.applicantsStar1,
                binding.applicantsStar2,
                binding.applicantsStar3,
                binding.applicantsStar4,
                binding.applicantsStar5
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
        val binding: ListitemEmployerMyJobsNegotiatingApplicantsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_negotiating_applicants,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}