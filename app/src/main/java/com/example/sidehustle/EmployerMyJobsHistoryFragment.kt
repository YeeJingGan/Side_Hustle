package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployerMyJobsHistoryBinding

class EmployerMyJobsHistoryFragment : Fragment() {
    lateinit var binding: FragmentEmployerMyJobsHistoryBinding
    lateinit var viewModel : EmployerMyJobsHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employer_my_jobs_history,container,false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployerMyJobsHistoryViewModel::class.java)


        var adapter = EmployerMyJobsHistoryJobsAdapter(emptyList(),viewModel,this)

        viewModel.historyJobs.observe(viewLifecycleOwner){
            adapter = EmployerMyJobsHistoryJobsAdapter(it,viewModel,this)
            binding.fragmentEmployerHistoryRecyclerView.adapter = adapter
        }

        binding.fragmentEmployerHistoryRecyclerView.adapter = adapter
        binding.fragmentEmployerHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }
}