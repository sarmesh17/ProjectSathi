package com.matrix.projectsathi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.matrix.projectsathi.domain.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardScreenViewModel @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth:FirebaseAuth
) : ViewModel() {

    private val _globalStatuses = MutableLiveData<List<Status>>()
    val globalStatuses: LiveData<List<Status>> get() = _globalStatuses

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchAllStatuses()
        fetchUserDetails()
    }

    private fun fetchAllStatuses() {
        database.reference.child("globalStatuses").get()
            .addOnSuccessListener { snapshot ->
                val detailedStatuses = mutableListOf<Status>()

                snapshot.children.forEach { statusSnapshot ->
                    val statusId = statusSnapshot.key ?: return@forEach
                    val userId = statusSnapshot.child("userId").value as? String ?: return@forEach
                    val timestamp = statusSnapshot.child("timestamp").value as? Long ?: 0L

                    // Fetch status details
                    database.reference.child("users").child(userId).child("statuses")
                        .child(statusId)
                        .get()
                        .addOnSuccessListener { statusDetailsSnapshot ->
                            val statusDetails = statusDetailsSnapshot.getValue(Status::class.java)
                            if (statusDetails != null) {
                                detailedStatuses.add(
                                    statusDetails.copy(
                                        userId = userId,
                                        timestamp = timestamp
                                    )
                                )
                            }

                            // If all statuses are fetched, update LiveData
                            if (detailedStatuses.size == snapshot.childrenCount.toInt()) {
                                _globalStatuses.value =
                                    detailedStatuses.sortedByDescending { it.timestamp }
                            }
                        }
                        .addOnFailureListener { exception ->
                            _error.value = "Failed to fetch status details: ${exception.message}"
                        }
                }
            }
            .addOnFailureListener { exception ->
                _error.value = "Failed to fetch global statuses: ${exception.message}"
            }
    }


    var firstName: String? = null
    var lastName: String? = null

    private fun fetchUserDetails() {
        val currentUser = auth.currentUser
            ?: // Handle the case where the user is not logged in
            return

        val userId = currentUser.uid
        database.reference.child("users").child(userId).get()
            .addOnSuccessListener { snapshot ->
                firstName = snapshot.child("firstName").value as? String
                lastName = snapshot.child("lastName").value as? String

                // Log or use these variables as needed
                println("First Name: $firstName, Last Name: $lastName")
            }
            .addOnFailureListener { exception ->
                println("Failed to fetch user details: ${exception.message}")
            }
    }
}
