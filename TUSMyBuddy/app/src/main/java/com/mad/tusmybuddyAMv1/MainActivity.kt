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
import com.mad.tusmybuddyAMv1.ui.BuddyRequest
import com.mad.tusmybuddyAMv1.ui.ChatScreen
import com.mad.tusmybuddyAMv1.ui.ChatViewModel
import com.mad.tusmybuddyAMv1.ui.ConnectScreen
import com.mad.tusmybuddyAMv1.ui.ConnectViewModel
import com.mad.tusmybuddyAMv1.ui.HomeScreen
import com.mad.tusmybuddyAMv1.ui.LoginScreen
import com.mad.tusmybuddyAMv1.ui.LoginViewModel
import com.mad.tusmybuddyAMv1.ui.NotificationMessages
import com.mad.tusmybuddyAMv1.ui.NotificationViewModel
import com.mad.tusmybuddyAMv1.ui.ProfileScreen
import com.mad.tusmybuddyAMv1.ui.RegistrationViewModel
import com.mad.tusmybuddyAMv1.ui.Screen
import com.mad.tusmybuddyAMv1.ui.SignUpScreen
import com.mad.tusmybuddyAMv1.ui.StartScreen
import com.mad.tusmybuddyAMv1.ui.StartScreenViewModel
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import androidx.annotation.RequiresApi
import android.Manifest
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.mad.tusmybuddyAMv1.ui.OtherProfileScreen
import com.mad.tusmybuddyAMv1.ui.PermissionDialog
import com.mad.tusmybuddyAMv1.ui.RationaleDialog
import com.mad.tusmybuddyAMv1.ui.UserProfileScreen
import com.mad.tusmybuddyAMv1.ui.UserProfileViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import android.content.Context
import android.net.Network
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mad.tusmybuddyAMv1.ui.theme.publicSans


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch{
            val token = Firebase.messaging.token.await()
            Log.d("FCM token:", token)
        }
        setContent {
            TUSMyBuddyTheme {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    RequestNotificationPermissionDialog()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    var showDialog by remember { mutableStateOf(true) }
                    SideEffect {
                        coroutineScope.launch {
                            showDialog = !checkInternetConnection(this@MainActivity)
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                coroutineScope.launch {
                                    showDialog = !checkInternetConnection(this@MainActivity)
                                }
                            },
                            title = { Text("") },
                            text = {
                                Column {
                                    Image(
                                        painter = painterResource(R.drawable.tus_logo_primary_eng_rgb),
                                        contentDescription = "Dialog Image",
                                        modifier = Modifier.size(100.dp)
                                    )
                                    Text("Error", fontFamily = publicSans,fontWeight = FontWeight.Bold,fontSize = 22.sp)
                                    Text("No internet connection", fontFamily = publicSans)
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    coroutineScope.launch {
                                        showDialog = !checkInternetConnection(this@MainActivity)
                                    }
                                }) {
                                    Text("OK")
                                }
                            }
                        )
                    }
                    NavGraph()
                    //StartScreen()
                    //val navController = rememberNavController()
                    //UserProfileScreen(navController, "dummy")
                    //ChatScreen(navController)
                    //ConnectScreen(navController, "dummy" )
                }
            }


        }

    }
}

//Deprecated, but is the only stable way to do it for now
private fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermissionDialog() {
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    if (!permissionState.status.isGranted) {
        if (permissionState.status.shouldShowRationale) RationaleDialog()
        else PermissionDialog { permissionState.launchPermissionRequest() }
    }
}


@Composable
fun NavGraph(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    val viewModel: RegistrationViewModel = viewModel()
    val viewModelL: LoginViewModel = viewModel()
    val viewModelLL: StartScreenViewModel = viewModel()
    val viewModelLLL: ChatViewModel = viewModel()
    val viewModelRL: ConnectViewModel = viewModel()
    val viewModelRRL: NotificationViewModel = viewModel()
    val viewModeNL: UserProfileViewModel = viewModel()
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

        // Accept a user ID as an argument in the Connect screen
        composable("connect/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            ConnectScreen(navController,viewModelRL, userId)
        }

        // Accept a user ID as an argument in the Buddy Request screen
        composable("buddyrequest/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            BuddyRequest(navController,viewModelRRL,userId)
        }

        // Accept a user ID as an argument in the Notification Messages screen
        composable("notificationmessages/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            NotificationMessages(navController,viewModelRRL,userId)
        }

        // Accept a user ID as an argument in the User Profile screen
        composable("userprofilescreen/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            UserProfileScreen(navController,viewModeNL, userId)
        }

        // Accept a user ID as an argument in the User Profile screen
        composable("otherprofilescreen/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            OtherProfileScreen(navController,viewModeNL, userId)
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