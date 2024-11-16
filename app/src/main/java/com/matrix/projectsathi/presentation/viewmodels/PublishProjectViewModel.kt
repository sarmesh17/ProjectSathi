package com.matrix.projectsathi.presentation.viewmodels



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublishProjectViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : ViewModel() {

    private val _publishState = MutableLiveData<PublishState>()
    val publishState: LiveData<PublishState> get() = _publishState

    fun publishStatus(
        projectDescription: String,
        projectDuration: Int,
        durationType: String,
        startTime: String,
        endTime: String,
        skillsRequired: List<String>,
        projectGoal: String,
        technologiesUsed: List<String>
    ) {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _publishState.value = PublishState.Error("User not logged in")
            return
        }

        val userId = currentUser.uid // Identify the user by UID
        val statusId = database.reference.child("statuses").push().key ?: ""
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
            "timestamp" to System.currentTimeMillis()
        )

        // Add status under the user's node
        database.reference.child("users").child(userId).child("statuses").child(statusId).setValue(status)
            .addOnSuccessListener {
                // Add status reference to globalStatuses node
                database.reference.child("globalStatuses").child(statusId).setValue(mapOf(
                    "userId" to userId,
                    "timestamp" to System.currentTimeMillis()
                )).addOnSuccessListener {
                    _publishState.value = PublishState.Success
                }.addOnFailureListener { exception ->
                    _publishState.value = PublishState.Error("Failed to add to global: ${exception.message}")
                }
            }
            .addOnFailureListener { exception ->
                _publishState.value = PublishState.Error("Failed to add status: ${exception.message}")
            }
    }
}

sealed class PublishState {
    object Loading : PublishState()
    object Success : PublishState()
    data class Error(val message: String) : PublishState()
}
