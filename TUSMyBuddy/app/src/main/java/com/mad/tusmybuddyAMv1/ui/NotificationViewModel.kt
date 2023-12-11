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

class NotificationViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()


    fun fetchMessageSenders(currentUserId: String): Flow<List<Pair<String, User>>> = callbackFlow {
        val usersRef = database.getReference("UsersData")
        val messagesRef = database.getReference("Messages")
        val messageSenders = mutableSetOf<String>()

        val listener = messagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageSenders.clear()
                for (childSnapshot in snapshot.children) {
                    val message = childSnapshot.getValue(Message::class.java)
                    if (message != null && message.receiverId == currentUserId) {
                        messageSenders.add(message.senderId)
                    }
                }

                val senderUsers = mutableListOf<Pair<String, User>>()
                for (senderId in messageSenders) {
                    val senderRef = usersRef.child(senderId)
                    senderRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(senderSnapshot: DataSnapshot) {
                            val sender = senderSnapshot.getValue(User::class.java)
                            if (sender != null) {
                                senderUsers.add(Pair(senderId, sender))
                                trySend(senderUsers.toList())
                            }
                        }

                        override fun onCancelled(senderError: DatabaseError) {
                            // Handle error
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose { messagesRef.removeEventListener(listener) }
    }


    //Get the pending requests
    fun fetchPendingRequests(currentUserId: String): Flow<List<Pair<String, User>>> = callbackFlow {
        val usersRef = database.getReference("UsersData")
        val notificationsRef = database.getReference("Notifications")
        val pendingRequests = mutableSetOf<String>()

        val listener = notificationsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pendingRequests.clear()
                for (childSnapshot in snapshot.children) {
                    val notification = childSnapshot.getValue(Notification::class.java)
                    if (notification != null && notification.receiverId == currentUserId && !notification.accepted!!) {
                        notification.senderId?.let { pendingRequests.add(it) }
                    }
                }

                val requestSenders = mutableListOf<Pair<String, User>>()
                for (senderId in pendingRequests) {
                    val senderRef = usersRef.child(senderId)
                    senderRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(senderSnapshot: DataSnapshot) {
                            val sender = senderSnapshot.getValue(User::class.java)
                            if (sender != null) {
                                requestSenders.add(Pair(senderId, sender))
                                trySend(requestSenders.toList())
                            }
                        }

                        override fun onCancelled(senderError: DatabaseError) {
                            // Handle error
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose { notificationsRef.removeEventListener(listener) }
    }


    //Accept Buddy Request
    fun acceptBuddyRequest(currentUserId: String, senderId: String) {
        val currentUserRef = database.getReference("UsersData").child(currentUserId)
        val notificationsRef = database.getReference("Notifications")

        // Add the sender to the current user's buddies list
        currentUserRef.child("buddies").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buddies = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
                if (!buddies.contains(senderId)) {
                    currentUserRef.child("buddies").setValue(buddies + senderId)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Update the notification to accepted
        notificationsRef.orderByChild("senderId").equalTo(senderId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val notification = childSnapshot.getValue(Notification::class.java)
                    if (notification != null && notification.receiverId == currentUserId && !notification.accepted!!) {
                        childSnapshot.ref.child("accepted").setValue(true)
                    }
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