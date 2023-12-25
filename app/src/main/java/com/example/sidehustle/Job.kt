package com.example.sidehustle

import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

data class Job(
    val photoResId: Int,
    val jobName: String,
    val jobPrice: Int,
    val jobCity: String,
    val jobState: String,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val startTime: LocalTime = LocalTime.now(),
    val endTime: LocalTime = LocalTime.now()
)
