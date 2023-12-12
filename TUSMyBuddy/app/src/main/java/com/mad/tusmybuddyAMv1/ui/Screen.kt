package com.mad.tusmybuddyAMv1.ui

sealed class Screen(val route: String){
    object Login: Screen("login")
    object SignUp: Screen("signup")
    object Profile: Screen("profile")
    object Home: Screen("home")
    object Start: Screen("start")
    object Chat: Screen("chat")

    object Connect: Screen("connect")

    object NotificationMessages: Screen("notificationmessages")

    object BuddyRequest: Screen("buddyrequest")

    object UserProfileScreen: Screen("userprofilescreen")

}


//Want to pass in a value
/*
    object Profile : Screen("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }

    --NavHost
    composable(Screen.Profile.route) { backStackEntry ->
    val userId = backStackEntry.arguments?.getString("userId")
    ProfileScreen(navController, userId)
}

    --Kt File
    Button(onClick = { navController.navigate(Screen.Profile.createRoute("123")) }) {
    Text("Go to Profile")
}
 */