package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployerMyJobsHistoryBinding

class EmployerMyJobsHistoryFragment : Fragment() {
    lateinit var binding: FragmentEmployerMyJobsHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employer_my_jobs_history,container,false)

        val adapter = EmployerMyJobsHistoryJobsAdapter(populateJobs())

        binding.fragmentEmployerHistoryRecyclerView.adapter = adapter
        binding.fragmentEmployerHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    private fun populateJobs(): List<EntityJob> {
        return listOf(
            EntityJob(
                1,
                1,
                "Job1",
                "JobState1",
                70,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription1"
            ),
            EntityJob(
                2,
                2,
                "Job2",
                "JobState2",
                80,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription2"
            ),
            EntityJob(
                3,
                3,
                "Job3",
                "JobState3",
                90,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription3"
            )
        )
    }
}