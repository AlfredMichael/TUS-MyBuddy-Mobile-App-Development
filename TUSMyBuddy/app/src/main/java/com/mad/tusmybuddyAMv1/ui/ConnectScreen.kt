package com.mad.tusmybuddyAMv1.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreen(navController: NavController,viewModel: ConnectViewModel = viewModel(), userId: String?){
    val similarUsers = userId?.let { viewModel.fetchSimilarUsers(it).collectAsState(initial = emptyList()) }

    Scaffold(
        topBar = {ConnectScreenTopAppBar(navController, viewModel)},
        bottomBar = {ConnectBottomNavigationBar(navController, userId)}


    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
                similarUsers?.value?.let { ConnectScreenMain(it, userId, navController, viewModel)}


            }
            Text(text = "User ID: $userId")

        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreenTopAppBar(navController: NavController, viewModel: ConnectViewModel=viewModel()){
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.tus_logo_primary_eng_rgb),
                    contentDescription = stringResource(R.string.home_screen_image_content_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.size_start_screen))
                        //.clip(CircleShape)
                        .aspectRatio(1f)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + "+353874864779")
                context.startActivity(dialIntent)
            }) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    modifier = Modifier.size(25.dp),
                    contentDescription = "Call Alfred"
                )
            }
            IconButton(onClick = {
                viewModel.logoutUser()
                navController.navigate(Screen.Home.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    modifier = Modifier.size(25.dp),
                    contentDescription = "Log out"
                )
            }
        }

    )

}

@Composable
fun ConnectScreenMain(similarUsers: List<Pair<String, User>>, currentUserId: String?, navController: NavController,viewModel: ConnectViewModel = viewModel()){
    for ((userId, user) in similarUsers) {
        val isBuddy = currentUserId?.let { viewModel.isBuddy(it, userId).collectAsState(initial = false) }
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 9.dp, end = 9.dp, top = 12.dp, bottom = 3.dp),
            shape = MaterialTheme.shapes.medium) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = user.profilePicture,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(165.dp)
                        .clickable(onClick = {
                            //Navigate to the other profile screen
                            navController.navigate("otherprofilescreen/$userId")
                        }),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = user.fullName ?: "",
                    fontFamily = publicSans,
                    fontSize =20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )


                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = user.course ?: "",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(7.dp))

                if (isBuddy != null) {
                    Button(onClick = { viewModel.sendBuddyRequest(currentUserId, userId) },
                        enabled = !isBuddy.value) {
                        Text(text = if (isBuddy.value) "Buddy Request Sent" else "Send Buddy Request")
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    }


}

@Composable
fun ConnectBottomNavigationBar(navController: NavController, userId: String?) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {
                userId?.let {
                    //Navigate to the Connect screen
                    navController.navigate("start/${it}")
                }
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.Home, contentDescription = "Home Icon")
                    Text(text = "Home", fontSize = 12.sp)
                }
            }
            IconButton(onClick = { /* do something */ }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Maps Icon")
                    Text(text = "Maps", fontSize = 12.sp)
                }
            }
            IconButton(onClick = {
                userId?.let {
                    //Navigate to the user profile screen
                    navController.navigate("userprofilescreen/${it}")
                }
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.Person, contentDescription = "User Icon")
                    Text(text = "Profile", fontSize = 12.sp)
                }
            }
            IconButton(onClick = {
                userId?.let {
                    //Navigate to the Connect screen
                    navController.navigate("notificationmessages/${it}")
                }
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Filled.Info, contentDescription = "Notifications Icon")
                    Text(text = "Notif..", fontSize = 12.sp)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ConnectScreenPreview(viewModel: ConnectViewModel = viewModel()) {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        ConnectScreen(navController,viewModel, "dummy")
    }
}



@Preview
@Composable
fun ConnectScreenDarkPreview(viewModel: ConnectViewModel = viewModel()) {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        ConnectScreen(navController,viewModel,"dummy")
    }
}