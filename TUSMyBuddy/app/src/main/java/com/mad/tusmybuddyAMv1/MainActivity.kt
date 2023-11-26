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
import com.mad.tusmybuddyAMv1.ui.HomeScreen
import com.mad.tusmybuddyAMv1.ui.LoginScreen
import com.mad.tusmybuddyAMv1.ui.ProfileScreen
import com.mad.tusmybuddyAMv1.ui.RegistrationViewModel
import com.mad.tusmybuddyAMv1.ui.Screen
import com.mad.tusmybuddyAMv1.ui.SignUpScreen
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
                }
            }

        }
    }
}

@Composable
fun NavGraph(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    val viewModel: RegistrationViewModel = viewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController, viewModel) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}