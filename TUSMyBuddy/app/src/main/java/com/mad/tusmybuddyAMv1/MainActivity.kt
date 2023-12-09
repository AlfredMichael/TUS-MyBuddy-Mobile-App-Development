package com.mad.tusmybuddyAMv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mad.tusmybuddyAMv1.ui.ChatScreen
import com.mad.tusmybuddyAMv1.ui.ChatViewModel
import com.mad.tusmybuddyAMv1.ui.HomeScreen
import com.mad.tusmybuddyAMv1.ui.LoginScreen
import com.mad.tusmybuddyAMv1.ui.LoginViewModel
import com.mad.tusmybuddyAMv1.ui.ProfileScreen
import com.mad.tusmybuddyAMv1.ui.RegistrationViewModel
import com.mad.tusmybuddyAMv1.ui.Screen
import com.mad.tusmybuddyAMv1.ui.SignUpScreen
import com.mad.tusmybuddyAMv1.ui.StartScreen
import com.mad.tusmybuddyAMv1.ui.StartScreenViewModel
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUSMyBuddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                    //StartScreen()
                    //val navController = rememberNavController()
                    //ChatScreen(navController)
                }
            }

        }
    }
}

@Composable
fun NavGraph(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    val viewModel: RegistrationViewModel = viewModel()
    val viewModelL: LoginViewModel = viewModel()
    val viewModelLL: StartScreenViewModel = viewModel()
    val viewModelLLL: ChatViewModel = viewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController,viewModelL ) }
        composable(Screen.SignUp.route) { SignUpScreen(navController, viewModel) }
        // Accept a user ID as an argument in the Profile screen
        composable("profile/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            ProfileScreen(navController, userId)
        }

        // Accept a user ID as an argument in the Start screen
        composable("start/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            StartScreen(navController,viewModelLL, userId)
        }

        //Accept the user id, email and fullName as arguments
        composable("chat/{userId}/{email}/{buddyId}/{fullName}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val buddyId = backStackEntry.arguments?.getString("buddyId")
            val email = backStackEntry.arguments?.getString("email")
            val fullName = backStackEntry.arguments?.getString("fullName")
            ChatScreen(navController,viewModelLLL, userId, buddyId, email, fullName)
        }



    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}