package com.mad.tusmybuddyAMv1.ui

data class Notification(
    val senderId: String? = null,
    val receiverId: String? = null,
    var accepted: Boolean? = false
)

