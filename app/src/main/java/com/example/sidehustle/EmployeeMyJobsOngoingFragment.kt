package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsOngoingBinding
class EmployeeMyJobsOngoingFragment : Fragment() {

    lateinit var binding: FragmentEmployeeMyJobsOngoingBinding
    lateinit var viewModel : EmployeeMyJobsOngoingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employee_my_jobs_ongoing,container,false)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployeeMyJobsOngoingViewModel::class.java)


        var adapter = EmployeeMyJobsOngoingAdapter(emptyList())

        viewModel.ongoingJobs.observe(viewLifecycleOwner){
            adapter = EmployeeMyJobsOngoingAdapter(it)
            binding.employeeOngoingRecyclerView.adapter = adapter
        }

        binding.employeeOngoingRecyclerView.adapter = adapter
        binding.employeeOngoingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}