package com.mad.tusmybuddyAMv1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConnectViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    fun fetchSimilarUsers(userId: String): Flow<List<Pair<String, User>>> = callbackFlow {
        val usersRef = database.getReference("UsersData")
        var currentUser: User? = null

        val currentUserListener = usersRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(User::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        val listener = usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val similarUsers = mutableListOf<Pair<String, User>>()
                for (childSnapshot in snapshot.children) {
                    val user = childSnapshot.getValue(User::class.java)
                    if (user != null && childSnapshot.key != userId &&
                        (user.interests?.intersect((currentUser?.interests ?: listOf()).toSet())?.isNotEmpty() == true ||
                                user.hobbies?.intersect((currentUser?.hobbies ?: listOf()).toSet())?.isNotEmpty() == true ||
                                user.skills?.intersect((currentUser?.skills ?: listOf()).toSet())?.isNotEmpty() == true)) {
                        similarUsers.add(Pair(childSnapshot.key!!, user))
                    }
                }
                trySend(similarUsers.toList())
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose {
            usersRef.child(userId).removeEventListener(currentUserListener)
            usersRef.removeEventListener(listener)
        }
    }

    //Checks if the user being retrieved is already a buddy
    fun isBuddy(currentUserId: String, potentialBuddyId: String): Flow<Boolean> = callbackFlow {
        val currentUserRef = database.getReference("UsersData").child(currentUserId).child("buddies")
        val listener = currentUserRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buddies = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
                trySend(buddies.contains(potentialBuddyId))
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose { currentUserRef.removeEventListener(listener) }
    }


    //Send buddy request
    fun sendBuddyRequest(currentUserId: String, buddyId: String) {
        val currentUserRef = database.getReference("UsersData").child(currentUserId)
        val notificationsRef = database.getReference("Notifications")
        currentUserRef.child("buddies").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buddies = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
                if (!buddies.contains(buddyId)) {
                    currentUserRef.child("buddies").setValue(buddies + buddyId)
                    val notification = Notification(currentUserId, buddyId, false)
                    notificationsRef.push().setValue(notification)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
    fun logoutUser() {
        // Sign out the user
        auth.signOut()

    }






}