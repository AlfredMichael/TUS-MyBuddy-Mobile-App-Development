package com.mad.tusmybuddyAMv1.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")


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
            }
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