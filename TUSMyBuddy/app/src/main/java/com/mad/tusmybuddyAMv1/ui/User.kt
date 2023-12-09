package com.mad.tusmybuddyAMv1.ui

import android.net.Uri

data class User(
    val fullName: String? = null,
    val email: String? = null,
    val course: String? = null,
    val year: Int? = null,
    val username: String? = null,
    val hobbies: List<String>? = null,
    val skills: List<String>? = null,
    val interests: List<String>? = null,
    val buddies: List<String>? = null,
    val bio: String? = null,
    val buddiesCount: Int? = null,
    val profilePicture: String? = null,
    //val profilePicture: Uri? = null,
    val reputationPoints: Int? = null
)
