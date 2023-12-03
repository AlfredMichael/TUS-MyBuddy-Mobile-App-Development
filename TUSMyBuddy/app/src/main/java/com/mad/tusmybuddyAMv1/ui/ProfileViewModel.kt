package com.mad.tusmybuddyAMv1.ui

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    //Fetch Users data
    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            val usersDataRef = database.getReference("UsersData")
            try {
                val dataSnapshot = withContext(Dispatchers.IO) {
                    val task = usersDataRef.child(userId).get()
                    Tasks.await(task)
                }
                val user = dataSnapshot.getValue(User::class.java)
                _userData.postValue(user)
            } catch (e: Exception) {
                // Log the exception
                Log.e("ProfileViewModel", "Failed to fetch user data", e)
                errorMessage.postValue("Could not user data, please try again later!")
            }
        }
    }

    //Upload an image
    fun uploadImage(userId: String, imageUri: Uri) {
        val storageRef = storage.reference.child("profilePictures/$userId")
        val uploadTask = storageRef.putFile(imageUri)

        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val usersDataRef = database.getReference("UsersData")
                usersDataRef.child(userId).child("profilePicture").setValue(uri.toString())
            }
        }.addOnFailureListener {
            // Handle any errors
            errorMessage.postValue("Image Upload Failed, please try again later!")
        }
    }

    //Update users data
    fun updateUserData(userId: String, username: String, bio: String, skills: String, hobbies: String, interests: String) {
        if (username.isBlank() || bio.isBlank() || skills.isBlank() || hobbies.isBlank() || interests.isBlank()) {
            // One or more fields are blank, show an error message
            errorMessage.postValue("All fields are required!")
            return
        } else {
            val usersDataRef = database.getReference("UsersData").child(userId)
            val userData = mapOf(
                "username" to username,
                "bio" to bio,
                "skills" to skills.split(",").map { it.trim() },
                "hobbies" to hobbies.split(",").map { it.trim() },
                "interests" to interests.split(",").map { it.trim() },
                "buddiesCount" to 0,
                "reputationPoints" to 0
            )
            usersDataRef.updateChildren(userData)

        }

    }



    /*
    fun fetchUserData(userId: String) {
        val usersDataRef = FirebaseDatabase.getInstance().getReference("UsersData")
        usersDataRef.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val user = dataSnapshot.getValue(User::class.java)
            _userData.postValue(user)
        }
    }*/
}