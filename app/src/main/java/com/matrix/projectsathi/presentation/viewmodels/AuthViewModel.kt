package com.matrix.projectsathi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
): ViewModel() {

    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    private val _loginState = MutableLiveData<LoginState>(LoginState.Default)
    val loginState: LiveData<LoginState> get() = _loginState

    // Sign-up method
    fun signUp(firstName: String, lastName: String, email: String, password: String) {
        _signUpState.value = SignUpState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid ?: ""
                    val user = mapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email
                    )

                    database.reference.child("users").child(userId).setValue(user)
                        .addOnSuccessListener {
                            _signUpState.value = SignUpState.Success
                        }
                        .addOnFailureListener { exception ->
                            _signUpState.value = SignUpState.Error(exception.message ?: "Failed to save user data")
                        }
                } else {
                    _signUpState.value = SignUpState.Error(task.exception?.message ?: "Sign-Up Failed")
                }
            }
    }

    // Login method
    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error(task.exception?.message ?: "Login Failed")
                }
            }
    }
}

sealed class SignUpState {
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}

sealed class LoginState {
    data object Default :LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
