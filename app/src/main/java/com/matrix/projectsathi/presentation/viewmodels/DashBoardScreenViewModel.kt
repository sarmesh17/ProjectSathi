package com.matrix.projectsathi.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.matrix.projectsathi.domain.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardScreenViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : ViewModel() {

    // Holds the list of statuses
    val statuses = mutableStateOf<List<Status>>(emptyList())

    // Holds any potential error message
    val errorMessage = mutableStateOf<String?>(null)

    init {
        fetchStatuses()
    }

    private fun fetchStatuses() {
        val statusRef = firebaseDatabase.getReference("statuses")

        statusRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val statusList = snapshot.children.mapNotNull { child ->
                    try {
                        val caption = child.child("caption").getValue(String::class.java) ?: ""
                        val amount = child.child("amount").getValue(Double::class.java) ?: 0.0
                        val images = child.child("images").children.mapNotNull { it.getValue(String::class.java) }
                        val skillsRequired = child.child("skillsRequired").children.mapNotNull { it.getValue(String::class.java) }

                        Status(
                            caption = caption,
                            amount = amount,
                            images = images,
                            skillsRequired = skillsRequired
                        )
                    } catch (e: Exception) {
                        null // Ignore invalid data
                    }
                }
                statuses.value = statusList // Update the state
                errorMessage.value = null // Clear any previous errors
            }

            override fun onCancelled(error: DatabaseError) {
                errorMessage.value = "Failed to fetch statuses: ${error.message}" // Update error message
            }
        })
    }
}
