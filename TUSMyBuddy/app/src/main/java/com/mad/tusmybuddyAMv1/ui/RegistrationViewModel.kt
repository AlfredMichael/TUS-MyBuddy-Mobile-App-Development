package com.mad.tusmybuddyAMv1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistrationViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //Firebase Realtime Database Server is in belgium
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://tus-mybuddy-default-rtdb.europe-west1.firebasedatabase.app/")

    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    fun registerUser(email: String, fullName: String, password: String) {
        // Query the student data in Firebase Realtime Database
        val studentDataRef = database.getReference("StudentData")
        val query = studentDataRef.orderByChild("StudentEmail").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // The email exists in the student data, proceed with registration
                    val studentRecord = dataSnapshot.children.iterator().next().getValue(Student::class.java)
                    if (studentRecord != null) {
                        createUser(email, fullName, password, studentRecord)
                    } else {
                        // Handle the case where studentRecord is null
                    }
                } else {
                    // The email does not exist in the student data, handle this case
                    errorMessage.postValue("The email does not exist in the student data.")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                errorMessage.postValue(databaseError.message)
            }
        })
    }

    //Create User
    private fun createUser(email: String, fullName: String, password: String, studentRecord: Student) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is registered, now update the UsersData
                    val usersDataRef = database.getReference("UsersData")
                    val userRecord = User(fullName, email, studentRecord.Course, studentRecord.Year) // replace with your User class
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        usersDataRef.child(currentUser.uid).setValue(userRecord)
                    } else {
                        // Handle the case where currentUser is null
                    }

                    // Get the user ID
                    val userId = auth.currentUser?.uid
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }
}