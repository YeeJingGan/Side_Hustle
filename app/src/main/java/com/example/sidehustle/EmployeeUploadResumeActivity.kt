package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar

class EmployeeUploadResumeActivity : AppCompatActivity() {

    private var resumePdfFile: PdfFile? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_upload_resume)

        val toolbar = findViewById<MaterialToolbar>(R.id.employee_upload_resume_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val cancelButton = findViewById<ImageButton>(R.id.employee_upload_resume_cancel_button)
        val uploadButton = findViewById<Button>(R.id.employee_upload_resume_upload_button)

        cancelButton.setOnClickListener {
            deleteCurrentResume()
        }

        uploadButton.setOnClickListener {
            // TODO: Implement file picker here
            val newResumePdfFile = PdfFile("TEST_FILE_NAME.pdf","test_path")
            handleResumeUpload(newResumePdfFile)
        }

        updateUI()
    }

    private fun deleteCurrentResume(){
        resumePdfFile = null

        updateUI()
    }

    private fun handleResumeUpload(newResumePdfFile: PdfFile){

        resumePdfFile = newResumePdfFile

        updateUI()
    }

    private fun updateUI(){
        val resumeFileLayout = findViewById<ConstraintLayout>(R.id.employee_upload_resume_file_layout)
        val pdfFileNameView = findViewById<TextView>(R.id.employee_upload_resume_file_name_textview)

        if(resumePdfFile!=null){
            resumeFileLayout.visibility = View.VISIBLE
            pdfFileNameView.text = resumePdfFile!!.fileName
        }else{
            resumeFileLayout.visibility = View.GONE
        }
    }
}