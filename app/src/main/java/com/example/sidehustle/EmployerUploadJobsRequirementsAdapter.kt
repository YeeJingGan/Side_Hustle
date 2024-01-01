package com.example.sidehustle

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sidehustle.databinding.EmployerUploadJobRequirementItemBinding
import com.example.sidehustle.databinding.FragmentEmployerHomeApprovedJobsBinding

class EmployerUploadJobsRequirementsAdapter(private val requirements: MutableList<String>) :
    RecyclerView.Adapter<EmployerUploadJobsRequirementsAdapter.ViewHolder>() {


    class ViewHolder(private val binding: EmployerUploadJobRequirementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val requirementText = binding.requirementEdittext
    }

    override fun getItemCount(): Int {
        return requirements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val requirement = requirements[position]

        // Set the text in the EditText
        holder.requirementText.setText(requirement)

        // Update the list when text changes
        holder.requirementText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    requirements[adapterPosition] = s.toString()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: EmployerUploadJobRequirementItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.employer_upload_job_requirement_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    fun addRequirement() {
        requirements.add("")
        notifyItemInserted(requirements.size - 1)
    }

    fun removeRequirement(recyclerView: RecyclerView) {
        if (requirements.isNotEmpty()) {
            val lastPosition = requirements.size - 1
            val lastRequirementHolder =
                recyclerView.findViewHolderForAdapterPosition(lastPosition) as? ViewHolder
            lastRequirementHolder?.let {
                if (it.requirementText.hasFocus()) {
                    it.requirementText.clearFocus()
                }
                requirements.removeAt(lastPosition)
                notifyItemRemoved(lastPosition)
            }
        }
    }

    fun removeAllRequirements() {
        requirements.clear()
        requirements.add("")
        notifyDataSetChanged()
    }
}