package com.example.sidehustle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployerMyJobsApplicantDetailsLanguagesBinding

class EmployerMyJobsNegotiatingApplicantDetailsAdapter(private val languages: List<EntityLanguage>) :
    RecyclerView.Adapter<EmployerMyJobsNegotiatingApplicantDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListitemEmployerMyJobsApplicantDetailsLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(language: EntityLanguage) {
            binding.language = language
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployerMyJobsApplicantDetailsLanguagesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.listitem_employer_my_jobs_applicant_details_languages,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}