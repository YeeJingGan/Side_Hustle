package com.example.sidehustle

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LegacyJobAdapter(private val context: Context, private val jobList: List<LegacyJob>) : BaseAdapter() {
    override fun getCount(): Int {
        return jobList.size
    }

    override fun getItem(position: Int): Any {
        return jobList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.layout_employee_home_job_item, parent, false)

        bindJobData(view, jobList[position])

        return view
    }

    private fun bindJobData(view: View, job: LegacyJob) {
        val jobProfilePhotoView = view.findViewById<ImageView>(R.id.job_profile_photo_imageview)
        val jobNameView = view.findViewById<TextView>(R.id.job_name_textview)
        val jobPriceView = view.findViewById<TextView>(R.id.job_price_textview)
        val jobPlaceView = view.findViewById<TextView>(R.id.job_place_textview)
        val detailsButton = view.findViewById<Button>(R.id.details_button)

        jobProfilePhotoView.setImageResource(job.photoResId)
        jobNameView.text = job.jobName
        jobPriceView.text = "RM " + job.jobPrice.toString()
        jobPlaceView.text = job.jobCity + ", " + job.jobState

        detailsButton.setOnClickListener {
            toJobDetails(it, job)
        }
    }

    private fun toJobDetails(view: View, job: LegacyJob) {
//      RMB Remove this
        val context = view.context
        val intent = Intent(context, EmployeeHomeJobDetailsActivity::class.java)

        context.startActivity(intent)

//        TODO("Navigate to Job Details, Need Job as argument")
    }
}
