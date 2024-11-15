package com.example.projectconnect.domain.model

data class ProjectDetailModel(
    val title: String,
    val description: String,
    val amount: String,
    val skillsRequired: String,
    val projectGoal: String,
    val technologiesUsed: String,
    val collaborators: List<String>,
    val projectDuration: String,
    val images: List<Int> // Images for project description or banner
)
