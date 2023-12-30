package com.example.sidehustle

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ReviewsCommentsAdapter(private val reviews: List<LegacyReview>) :
    RecyclerView.Adapter<ReviewsCommentsAdapter.ReviewsCommentsViewHolder>() {

    inner class ReviewsCommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reviewNameView = itemView.findViewById<TextView>(R.id.reviews_comments_name_textview)
        val reviewContentView =
            itemView.findViewById<TextView>(R.id.reviews_comments_content_textview)
        val reviewProfilePicture =
            itemView.findViewById<ImageView>(R.id.reviews_comments_profile_picture)
        val reviewStar1 = itemView.findViewById<ImageView>(R.id.reviews_comments_star_1)
        val reviewStar2 = itemView.findViewById<ImageView>(R.id.reviews_comments_star_2)
        val reviewStar3 = itemView.findViewById<ImageView>(R.id.reviews_comments_star_3)
        val reviewStar4 = itemView.findViewById<ImageView>(R.id.reviews_comments_star_4)
        val reviewStar5 = itemView.findViewById<ImageView>(R.id.reviews_comments_star_5)

        val starList = listOf(reviewStar1, reviewStar2, reviewStar3, reviewStar4, reviewStar5)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewsCommentsViewHolder, position: Int) {
        val currentView = reviews[position]

        holder.apply {
            reviewNameView.text = currentView.reviewName.toString()
            reviewContentView.text = currentView.reviewContent.toString()
            reviewProfilePicture.setImageResource(currentView.photoResId)
            for ((index, star) in starList.withIndex()) {
                if (index == currentView.reviewStarNum) {
                    break
                }

                star.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.theme_color
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsCommentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_my_profile_reviews_comments_item, parent, false)
        return ReviewsCommentsViewHolder(view)
    }
}