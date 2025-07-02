package com.example.facedx.ui.home

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    private val sdf = SimpleDateFormat("dd MMM yyyy", Locale("id"))
    fun format(epochMillis: Long): String = sdf.format(Date(epochMillis))
}
