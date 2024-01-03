package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsHistoryBinding

class EmployeeMyJobsHistoryFragment : Fragment() {
    lateinit var binding: FragmentEmployeeMyJobsHistoryBinding
    lateinit var viewModel : EmployeeMyJobsHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employee_my_jobs_history,container,false)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployeeMyJobsHistoryViewModel::class.java)


        var adapter = EmployeeMyJobsHistoryAdapter(emptyList())

        viewModel.historyJobs.observe(viewLifecycleOwner){
            adapter = EmployeeMyJobsHistoryAdapter(it)
            binding.employeeHistoryRecyclerview.adapter = adapter
        }

        binding.employeeHistoryRecyclerview.adapter = adapter
        binding.employeeHistoryRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}