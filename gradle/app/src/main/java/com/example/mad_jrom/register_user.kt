package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sidehustle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var employers: List<EntityEmployer> = emptyList()
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        populateSampleData()

        setListeners()


    }

    private fun populateSampleData() {
        employers = listOf(
            EntityEmployer(0,"IOI Sdn. Bhd.","ioi@mail.com","Employer4!"),
        )
    }

    private fun setListeners() {
        binding.apply {
            setListenerAndToAnotherActivity(mainButton1, EmployerHomeActivity::class.java)
            setListenerAndToAnotherActivity(mainButton2, EmployeeHomeActivity::class.java)

        }

        binding.mainButton3.setOnClickListener{
            viewModel.insertEmployers(employers)
            // TODO : DK Y INSERT THIS FIRST BEFORE INITIALIZING THE DATA INSIDE
        }
    }

    private fun setListenerAndToAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(it.context, destinationActivity))
        }
    }
}