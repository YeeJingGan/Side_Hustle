package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployerMyJobsNegotiatingBinding


class EmployerMyJobsNegotiatingFragment : Fragment() {
    lateinit var binding: FragmentEmployerMyJobsNegotiatingBinding
    lateinit var viewModel : EmployerMyJobsNegotiatingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_employer_my_jobs_negotiating, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployerMyJobsNegotiatingViewModel::class.java)


        var adapter = EmployerMyJobsNegotiatingJobsAdapter(emptyList(),viewModel,this)

        viewModel.negotiatingJobs.observe(viewLifecycleOwner){
            adapter = EmployerMyJobsNegotiatingJobsAdapter(it,viewModel,this)
            binding.employerNegotiatingRecyclerview.adapter = adapter
        }

        binding.employerNegotiatingRecyclerview.adapter = adapter
        binding.employerNegotiatingRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }
}