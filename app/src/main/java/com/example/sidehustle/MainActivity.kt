package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setListeners()
    }

    private fun setListeners(){
        binding.apply{
            setListenerAndToAnotherActivity(mainButton1,EmployerHomeActivity::class.java)
            setListenerAndToAnotherActivity(mainButton2,EmployerMyJobsNegotiatingActivity::class.java)
            setListenerAndToAnotherActivity(mainButton3,EmployerMyJobsOngoingActivity::class.java)
            setListenerAndToAnotherActivity(mainButton4,EmployerMyJobsHistoryActivity::class.java)
            setListenerAndToAnotherActivity(mainButton5,EmployerMyProfileActivity::class.java)
            setListenerAndToAnotherActivity(mainButton6,EmployerHomeJobDetailsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton7,EmployerHomeUploadJobsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton8,EmployerMyJobsHistoryEmployeeListCommentRatingActivity::class.java)
            setListenerAndToAnotherActivity(mainButton9,EmployerNegotiateWithEmployeeActivity::class.java)
            setListenerAndToAnotherActivity(mainButton10,EmployerSettingsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton11,EmployerMyJobsNegotiatingApplicantsListApplicantDetailsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton12,EmployerMyJobsOngoingEmployeeListEmployeeDetailsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton13,EmployeeSettingsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton14,EmployeeMyJobsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton15,EmployerMyJobsActivity::class.java)
            setListenerAndToAnotherActivity(mainButton15,EmployerSettingsActivity::class.java)



        }
    }
    private fun setListenerAndToAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(it.context,destinationActivity))
        }
    }
}