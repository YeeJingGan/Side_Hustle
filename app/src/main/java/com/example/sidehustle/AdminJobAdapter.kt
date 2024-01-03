package com.example.sidehustle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*class JobAdapter(private val context: Context, private val jobList: List<Job>) : BaseAdapter() {
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
            .inflate(R.layout.layout_admin_home_job_item, parent, false)

        bindJobData(view, jobList[position])

        return view
    }

    private fun bindJobData(view: View, job: Job) {
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
    }*/

class AdminJobAdapter(private val context: Context, private val jobList: List<AdminJob>) :
    RecyclerView.Adapter<AdminJobAdapter.JobViewHolder>() {

    // ViewHolder class for holding the views
    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_admin_home_job_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        bindJobData(holder.itemView, jobList[position])
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    private fun bindJobData(view: View, job: AdminJob) {
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


    private fun toJobDetails(view: View, job: AdminJob) {
//      RMB Remove this
        /*val context = view.context
        val intent = Intent(context, AdminHomeJobDetailsActivity::class.java)

        context.startActivity(intent)*/

//        TODO("Navigate to Job Details, Need Job as argument")
    }
}
