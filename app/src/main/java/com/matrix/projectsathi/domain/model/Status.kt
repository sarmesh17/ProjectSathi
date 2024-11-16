package com.matrix.projectsathi.domain.model

data class Status(
    val caption: String = "",
    val amount: Double = 0.0,
    val images: List<String> = emptyList(),
    val skillsRequired: List<String> = emptyList()
)