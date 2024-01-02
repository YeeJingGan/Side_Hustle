package com.example.sidehustle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.ListitemEmployeeHomeJobDetailsApplyJobsLanguagesBinding

class EmployeeHomeJobDetailsApplyJobsLanguageAdapter(private val languages: MutableList<String>) :
    RecyclerView.Adapter<EmployeeHomeJobDetailsApplyJobsLanguageAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListitemEmployeeHomeJobDetailsApplyJobsLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val languageText = binding.listitemEmployeeHomeLanguageEdittext
        val proficiencySpinner = binding.listitemEmployeeHomeLanguageSpinner
        var selectedProficiency = "Moderate"
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = languages[position]

        holder.languageText.setText(language)

        val proficiencyLevels = arrayOf("Poor", "Moderate", "Fluent", "Native")

        val proficiencyAdapter = ArrayAdapter(
            holder.itemView.context,
            android.R.layout.simple_spinner_item,
            proficiencyLevels
        )

        proficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        holder.proficiencySpinner.adapter = proficiencyAdapter

        holder.proficiencySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long
                ) {
                    holder.selectedProficiency = proficiencyLevels[position]
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
                    holder.selectedProficiency = "Moderate"
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListitemEmployeeHomeJobDetailsApplyJobsLanguagesBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.listitem_employee_home_job_details_apply_jobs_languages,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    fun addLanguage() {
        languages.add("")
        notifyItemInserted(languages.size - 1)
    }

    fun removeLanguage(recyclerView: RecyclerView) {
        if (languages.isNotEmpty()) {
            val lastPosition = languages.size - 1
            val lastRequirementHolder =
                recyclerView.findViewHolderForAdapterPosition(lastPosition) as? ViewHolder
            lastRequirementHolder?.let {
                if (it.languageText.hasFocus()) {
                    it.languageText.clearFocus()
                } else if (it.proficiencySpinner.hasFocus()) {
                    it.proficiencySpinner.clearFocus()
                }
                languages.removeAt(lastPosition)
                notifyItemRemoved(lastPosition)
            }
        }
    }

    fun removeAllLanguages() {
        languages.clear()
        languages.add("")
        notifyDataSetChanged()
    }
}