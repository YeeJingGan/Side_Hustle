package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployerMyJobsOngoingBinding


class EmployerMyJobsOngoingFragment : Fragment(), ViewModelStoreOwner {

    lateinit var binding: FragmentEmployerMyJobsOngoingBinding
    lateinit var viewModel : EmployerMyJobsOngoingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employer_my_jobs_ongoing,container,false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EmployerMyJobsOngoingViewModel::class.java)


        var adapter = EmployerMyJobsOngoingJobsAdapter(emptyList(),viewModel,this)

        viewModel.ongoingJobs.observe(viewLifecycleOwner){
            adapter = EmployerMyJobsOngoingJobsAdapter(it,viewModel,this)
            binding.fragmentEmployerOngoingRecyclerview.adapter = adapter
        }

        binding.fragmentEmployerOngoingRecyclerview.adapter = adapter
        binding.fragmentEmployerOngoingRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}