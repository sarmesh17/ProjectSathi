package com.matrix.projectsathi.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublishProjectViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val storage: FirebaseStorage
) : ViewModel() {

    private val _publishState = MutableLiveData<PublishState>()
    val publishState: LiveData<PublishState> get() = _publishState

    fun publishStatus(
        projectDescription: String,
        projectDuration: String,
        durationType: String,
        startTime: String,
        endTime: String,
        skillsRequired: List<String>,
        projectGoal: String,
        technologiesUsed: String,
        amount: String,
        images: List<Uri>
    ) {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _publishState.value = PublishState.Error("User not logged in")
            return
        }

        _publishState.value = PublishState.Loading
        val userId = currentUser.uid
        val statusId = database.reference.child("statuses").push().key ?: ""
        val uploadedImageUrls = mutableListOf<String>()

        // Upload images to Firebase Storage
        if (images.isNotEmpty()) {
            images.forEach { uri ->
                val imageRef = storage.reference.child("statusImages/$userId/$statusId/${uri.lastPathSegment}")
                imageRef.putFile(uri).addOnSuccessListener { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                        uploadedImageUrls.add(downloadUrl.toString())
                        if (uploadedImageUrls.size == images.size) {
                            // All images uploaded; publish status
                            saveStatus(
                                userId = userId,
                                statusId = statusId,
                                projectDescription = projectDescription,
                                projectDuration = projectDuration,
                                durationType = durationType,
                                startTime = startTime,
                                endTime = endTime,
                                skillsRequired = skillsRequired,
                                projectGoal = projectGoal,
                                technologiesUsed = technologiesUsed,
                                amount =amount,
                                images = uploadedImageUrls
                            )
                        }
                    }.addOnFailureListener { exception ->
                        _publishState.value =
                            PublishState.Error("Image upload failed: ${exception.message}")
                    }
                }.addOnFailureListener { exception ->
                    _publishState.value =
                        PublishState.Error("Image upload failed: ${exception.message}")
                }
            }
        } else {
            // No images to upload; publish status directly
            saveStatus(
                userId = userId,
                statusId = statusId,
                projectDescription = projectDescription,
                projectDuration = projectDuration,
                durationType = durationType,
                startTime = startTime,
                endTime = endTime,
                skillsRequired = skillsRequired,
                projectGoal = projectGoal,
                technologiesUsed = technologiesUsed,
                amount =amount,
                images = emptyList()
            )
        }
    }

    private fun saveStatus(
        userId: String,
        statusId: String,
        projectDescription: String,
        projectDuration: String,
        durationType: String,
        startTime: String,
        endTime: String,
        skillsRequired: List<String>,
        projectGoal: String,
        technologiesUsed: String,
        amount: String,
        images: List<String>
    ) {
        val status = mapOf(
            "projectDescription" to projectDescription,
            "projectDuration" to mapOf(
                "duration" to projectDuration,
                "type" to durationType
            ),
            "workingTime" to mapOf(
                "startTime" to startTime,
                "endTime" to endTime
            ),
            "skillsRequired" to skillsRequired,
            "projectGoal" to projectGoal,
            "technologiesUsed" to technologiesUsed,
            "amount" to amount,
            "images" to images,
            "timestamp" to System.currentTimeMillis()
        )

        // Add status under user's node
        database.reference.child("users").child(userId).child("statuses").child(statusId).setValue(status)
            .addOnSuccessListener {
                // Add status reference to globalStatuses node
                database.reference.child("globalStatuses").child(statusId).setValue(mapOf(
                    "userId" to userId,
                    "timestamp" to System.currentTimeMillis()
                )).addOnSuccessListener {
                    _publishState.value = PublishState.Success
                }.addOnFailureListener { exception ->
                    _publishState.value =
                        PublishState.Error("Failed to add to global: ${exception.message}")
                }
            }
            .addOnFailureListener { exception ->
                _publishState.value =
                    PublishState.Error("Failed to add status: ${exception.message}")
            }
    }
}

sealed class PublishState {
    object Loading : PublishState()
    object Success : PublishState()
    data class Error(val message: String) : PublishState()
}
