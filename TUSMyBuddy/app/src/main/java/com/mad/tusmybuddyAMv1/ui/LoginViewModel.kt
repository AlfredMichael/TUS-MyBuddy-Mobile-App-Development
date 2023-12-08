package com.mad.tusmybuddyAMv1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //LiveData to hold the user's id
    val userId = MutableLiveData<String?>()

    fun loginUser(email: String, password: String) {
        // Check if the email or password field is empty
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.postValue("Email or password cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is logged in, now you can get the user ID
                    val userIdm = auth.currentUser?.uid
                    if (userIdm != null) {
                        // Post the user ID to a LiveData
                        userId.postValue(userIdm)
                    } else {
                        // Handle the case where userId is null
                        errorMessage.postValue("An error has occurred, Please Try again later")
                    }
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }

}