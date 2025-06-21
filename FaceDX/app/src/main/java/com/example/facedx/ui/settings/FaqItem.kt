package com.example.facedx.ui.settings

data class FaqItem(
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)
