package com.mad.tusmybuddyAMv1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class StartScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    val authState = MutableLiveData<AuthState>()


    fun fetchBuddies(userId: String): Flow<List<Pair<String, User>>> = callbackFlow {
        val usersDataRef = database.getReference("UsersData").child(userId).child("buddies")
        val buddies = mutableListOf<Pair<String, User>>()
        val listener = usersDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                buddies.clear()
                for (childSnapshot in snapshot.children) {
                    val buddyId = childSnapshot.getValue(String::class.java)
                    if (buddyId != null && buddyId != "placeholder") {
                        // Fetch the buddy's details
                        val buddyDetailsRef = database.getReference("UsersData").child(buddyId)
                        buddyDetailsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(buddySnapshot: DataSnapshot) {
                                val buddy = buddySnapshot.getValue(User::class.java)
                                if (buddy != null) {
                                    buddies.add(Pair(buddyId, buddy))
                                    trySend(buddies.toList()) // Use 'trySend' instead of 'offer'
                                }
                            }

                            override fun onCancelled(buddyError: DatabaseError) {
                                // Handle error
                            }
                        })
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Clean up when the flow collection ends
        awaitClose { usersDataRef.removeEventListener(listener) }
    }

    fun logoutUser() {
        // Sign out the user
        auth.signOut()
        authState.value = AuthState.LOGGED_OUT

    }


    /*
    fun fetchBuddies(userId: String): Flow<List<User>> = callbackFlow {
        val usersDataRef = database.getReference("UsersData").child(userId).child("buddies")
        val buddies = mutableListOf<User>()
        val listener = usersDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                buddies.clear()
                for (childSnapshot in snapshot.children) {
                    val buddyId = childSnapshot.getValue(String::class.java)
                    if (buddyId != null && buddyId != "placeholder") {
                        // Fetch the buddy's details
                        val buddyDetailsRef = database.getReference("UsersData").child(buddyId)
                        buddyDetailsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(buddySnapshot: DataSnapshot) {
                                val buddy = buddySnapshot.getValue(User::class.java)
                                if (buddy != null) {
                                    buddies.add(buddy)
                                    trySend(buddies.toList()) // Use 'trySend' instead of 'offer'
                                }
                            }

                            override fun onCancelled(buddyError: DatabaseError) {
                                // Handle error
                            }
                        })
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Clean up when the flow collection ends
        awaitClose { usersDataRef.removeEventListener(listener) }
    }*/


}
enum class AuthState {
    LOGGED_IN,
    LOGGED_OUT
}