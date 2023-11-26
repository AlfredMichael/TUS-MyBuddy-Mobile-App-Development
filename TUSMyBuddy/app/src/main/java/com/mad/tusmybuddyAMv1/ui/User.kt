package com.mad.tusmybuddyAMv1.ui

data class User(
    val fullName: String? = null,
    val email: String? = null,
    val course: String? = null,
    val year: Int? = null,
    val hobbies: List<String>? = null,
    val skills: List<String>? = null,
    val interests: List<String>? = null,
    val bio: String? = null,
    val buddiesCount: Int? = null,
    val reputationPoints: Int? = null
)
