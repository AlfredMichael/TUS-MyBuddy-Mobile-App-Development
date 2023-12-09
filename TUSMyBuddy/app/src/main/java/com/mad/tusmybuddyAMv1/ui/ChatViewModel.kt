package com.mad.tusmybuddyAMv1.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ChatViewModel: ViewModel() {
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    fun isBuddy(senderId: String, receiverEmail: String): Flow<Boolean> = callbackFlow {
        val usersDataRef = database.getReference("UsersData")
        val listener = usersDataRef.orderByChild("email").equalTo(receiverEmail).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val user = childSnapshot.getValue(User::class.java)
                    if (user != null) {
                        val isBuddy = user.buddies?.contains(senderId)
                        if (isBuddy != null) {
                            trySend(isBuddy)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose { usersDataRef.removeEventListener(listener) }
    }

    fun fetchMessages(senderId: String, receiverId: String): Flow<List<Message>> = callbackFlow {
        val messagesRef = database.getReference("Messages")
        val messages = mutableListOf<Message>()
        val listener = messagesRef.orderByChild("date").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                for (childSnapshot in snapshot.children) {
                    val message = childSnapshot.getValue(Message::class.java)
                    if (message != null &&
                        ((message.senderId == senderId && message.receiverId == receiverId) ||
                                (message.senderId == receiverId && message.receiverId == senderId))) {
                        messages.add(message)
                        Log.d("ViewModel", "Fetched message: $message")  // Log the message
                        trySend(messages.toList())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        awaitClose { messagesRef.removeEventListener(listener) }
    }

    //Send a message
    fun addMessage(senderId: String, receiverId: String, content: String) {
        val messagesRef = database.getReference("Messages")
        val date = System.currentTimeMillis()  // Get the current time
        val message = Message(senderId, receiverId, content, date)
        messagesRef.push().setValue(message)  // Add the message to Firebase
    }

    //Block a user
    fun removeBuddy(userId: String, buddyId: String) {
        val buddiesRef = database.getReference("UsersData").child(userId).child("buddies")
        buddiesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buddies = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {})
                if (buddies != null) {
                    val updatedBuddies = buddies.filter { it != buddyId }  // Remove the buddyId
                    buddiesRef.setValue(updatedBuddies)  // Update the buddies list in Firebase
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }




}