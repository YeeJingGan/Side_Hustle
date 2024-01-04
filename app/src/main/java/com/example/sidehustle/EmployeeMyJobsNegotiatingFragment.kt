package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsNegotiatingBinding


class EmployeeMyJobsNegotiatingFragment : Fragment() {
    lateinit var binding: FragmentEmployeeMyJobsNegotiatingBinding
    lateinit var viewModel : EmployeeMyJobsNegotiatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employee_my_jobs_negotiating,container,false)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployeeMyJobsNegotiatingViewModel::class.java)


        var adapter = EmployeeMyJobsNegotiatingAdapter(emptyList())
//
//        viewModel.negotiatingJobs.observe(viewLifecycleOwner){
//            adapter = EmployeeMyJobsNegotiatingAdapter(it)
//            binding.employeeNegotiatingRecyclerview.adapter = adapter
//        }

        binding.employeeNegotiatingRecyclerview.adapter = adapter
        binding.employeeNegotiatingRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }



}