package com.example.sidehustle

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemReviewsCommentsBinding
import kotlinx.coroutines.launch

class EmployerMyProfileReviewsCommentsAdapter(
    private val ratings: List<EntityRating>,
    private val viewModel: EmployerMyJobsNegotiatingApplicantDetailsViewModel
) :
    RecyclerView.Adapter<EmployerMyProfileReviewsCommentsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListitemReviewsCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ratings: EntityRating) {
            binding.rating = ratings


            viewModel.viewModelScope.launch {
                val employer = viewModel.getEmployerByJobIDAndEmployeeID(ratings.jobID,ratings.employeeID)
                binding.username = employer.employerUsername
            }

            updateStarColors(ratings.rating)

            binding.executePendingBindings()
        }

        private fun updateStarColors(starsCount: Int) {
            val stars = arrayOf(
                binding.reviewsCommentsStar1,
                binding.reviewsCommentsStar2,
                binding.reviewsCommentsStar3,
                binding.reviewsCommentsStar4,
                binding.reviewsCommentsStar5
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
        return ratings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ratings[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemReviewsCommentsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.listitem_reviews_comments,
                parent,
                false
            )
        return ViewHolder(binding)
    }
}