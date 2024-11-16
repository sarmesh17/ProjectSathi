package com.matrix.projectsathi.domain.model

data class Status(
    val userId: String = "",
    val projectDescription: String = "",
    val projectDuration: ProjectDuration = ProjectDuration(),
    val workingTime: WorkingTime = WorkingTime(),
    val skillsRequired: List<String> = emptyList(),
    val projectGoal: String = "",
    val technologiesUsed: String = "",
    val amount:String="",
    val images: List<String> = emptyList(),
    val timestamp: Long = 0L
)

data class ProjectDuration(
    val duration: String = "",
    val type: String = "" // e.g., "days", "weeks", "months"
)

data class WorkingTime(
    val startTime: String = "", // e.g., "10:00 AM"
    val endTime: String = ""   // e.g., "6:00 PM"
)
