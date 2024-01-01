package com.example.sidehustle

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerHomeUploadJobsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EmployerHomeUploadJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeUploadJobsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home_upload_jobs)

        setSupportActionBar(binding.employerUploadJobToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        var wagesAmount = 0
        updateWages(wagesAmount)

        binding.uploadJobButtonPlus.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
        }

        binding.uploadJobButtonMinus.setOnClickListener {
            if (wagesAmount > 0) {
                wagesAmount -= 10
                updateWages(wagesAmount)
            }
        }

        binding.uploadJobInputStartDate.setOnClickListener {
            openDatePicker(binding.uploadJobInputStartDate)
        }

        binding.uploadJobInputEndDate.setOnClickListener {
            if (!binding.uploadJobInputStartDate.text.isNullOrEmpty()) {
                openDatePicker(binding.uploadJobInputEndDate)
            } else {
                Toast.makeText(this, "Please Pick A Start Date First", Toast.LENGTH_SHORT).show()
            }
        }

        binding.uploadJobTitleOneDay.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!binding.uploadJobInputStartDate.text.isNullOrEmpty()) {
                    binding.uploadJobInputEndDate.text = binding.uploadJobInputStartDate.text
                } else {
                    val today = SimpleDateFormat("DD/MM/yyyy", Locale.getDefault()).format(Date())
                    binding.uploadJobInputEndDate.text = today
                    binding.uploadJobInputStartDate.text = today
                }
            }
        }

        binding.uploadJobInputStartTime.setOnClickListener {
            openTimePicker(binding.uploadJobInputStartTime)
        }

        binding.uploadJobInputEndTime.setOnClickListener {
            if (!binding.uploadJobInputStartTime.text.isNullOrEmpty()) {
                openTimePicker(binding.uploadJobInputEndTime)
            } else {
                Toast.makeText(this, "Please Pick A Start Time First", Toast.LENGTH_SHORT).show()
            }
        }

        binding.uploadJobResetButton.setOnClickListener {
            showResetComfirmationDialog()
        }
    }

    private fun updateWages(amount: Int) {
        binding.uploadJobInputWages.text = "RM ${amount}"
    }

    private fun openDatePicker(textView: TextView) {
        val startDateTextView = binding.uploadJobInputStartDate
        val endDateTextView = binding.uploadJobInputEndDate
        val oneDayCheckBox = binding.uploadJobTitleOneDay

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                // Example: Print the selected date
                val selectedDateString =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)
                textView.text = selectedDateString

                if (!endDateTextView.text.isNullOrEmpty()) {
                    val endDateCalendar = Calendar.getInstance()
                    endDateCalendar.time = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).parse(endDateTextView.text.toString())!!
                    if (selectedDate.timeInMillis >= endDateCalendar.timeInMillis) {
                        oneDayCheckBox.isChecked = true
                    } else {
                        oneDayCheckBox.isChecked = false
                    }
                } else {
                    oneDayCheckBox.isChecked = true
                }

            },
            currentYear,
            currentMonth,
            currentDay
        )

        if (textView == endDateTextView && !startDateTextView.text.isNullOrEmpty()) {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = formatter.parse(startDateTextView.text.toString())

            datePickerDialog.datePicker.minDate = date!!.time
        } else {
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
        }

        datePickerDialog.show()
    }

    private fun openTimePicker(textView: TextView) {
        val startTimeTextView = binding.uploadJobInputStartTime
        val endTimeTextview = binding.uploadJobInputEndTime

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)

                val selectedTimeString =
                    SimpleDateFormat("HH:mm", Locale.getDefault()).format(selectedTime.time)
                textView.text = selectedTimeString

                if (textView == endTimeTextview) {
                    val validTime = Calendar.getInstance()
                    validTime.set(
                        Calendar.HOUR_OF_DAY,
                        SimpleDateFormat(
                            "HH:mm",
                            Locale.getDefault()
                        ).parse(startTimeTextView.text.toString())!!.hours
                    )
                    validTime.set(
                        Calendar.MINUTE,
                        SimpleDateFormat(
                            "HH:mm",
                            Locale.getDefault()
                        ).parse(startTimeTextView.text.toString())!!.minutes
                    )

                    if (selectedTime.timeInMillis < validTime.timeInMillis) {
                        textView.text = SimpleDateFormat(
                            "HH:mm",
                            Locale.getDefault()
                        ).format(validTime.time)

                        Toast.makeText(
                            applicationContext,
                            "End time cannot be earlier than start time",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        validTime.add(Calendar.HOUR_OF_DAY, 10)
                        if (selectedTime.timeInMillis > validTime.timeInMillis) {
                            textView.text = SimpleDateFormat(
                                "HH:mm",
                                Locale.getDefault()
                            ).format(validTime.time)
                            Log.i("TIME", "$selectedTime.timeInMillis")

                            Toast.makeText(
                                applicationContext,
                                "You should let employee work more than 10 hours",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            currentHour,
            currentMinute,
            true
        )

        timePickerDialog.show()
    }

    private fun showResetComfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Reset Confirmation")
        dialogBuilder.setMessage("Are you sure you want to reset?")
        dialogBuilder.setPositiveButton("Reset") { dialog, which ->
            reset()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun reset() {
        binding.apply {
            uploadJobInputJobTitle.text = null
            uploadJobInputStartDate.text = null
            uploadJobInputEndDate.text = null
            uploadJobTitleOneDay.isChecked = false
            uploadJobInputStartTime.text = null
            uploadJobInputEndTime.text = null
            uploadJobInputAddressLine1.text = null
            uploadJobInputAddressLine2.text = null
            uploadJobInputPostcode.text = null
            uploadJobInputState.setSelection(0)
            // TODO : RESET REQUIREMENTS
            uploadJobInputDescription.text = null
        }
    }
}