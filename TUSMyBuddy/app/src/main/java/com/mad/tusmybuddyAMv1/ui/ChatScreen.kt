package com.mad.tusmybuddyAMv1.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, viewModel: ChatViewModel = viewModel(), userId: String?, email:String?, buddyId: String?, fullName: String?){
    /* -- Mistake - do not uncomment, tried using id and emails to retrieve messages
    val messages = userId?.let { userId ->
        email?.let { email ->
            viewModel.fetchMessages(userId, email).collectAsState(initial = emptyList())
        }
    } ?: mutableStateOf(emptyList())*/

    val messages = buddyId?.let { buddyId ->
        userId?.let { userId ->
            viewModel.fetchMessages(userId, buddyId).collectAsState(initial = emptyList())
        }
    } ?: mutableStateOf(emptyList())

    val isBuddy = userId?.let { userId ->
        email?.let { email ->
            viewModel.isBuddy(userId, email).collectAsState(initial = false)
        }
    } ?: mutableStateOf(false)
    
    
    var message by remember { mutableStateOf("") }
    Scaffold(
        topBar = {ChatScreenTopAppBar(navController, fullName, viewModel, userId,buddyId)}

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxSize()) {

                Log.d("Composable", "Received messages: ${messages.value}")
                messages.value.forEach { message ->
                    if (message.senderId == userId) {
                        // Outgoing message bubble
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Surface(
                                shape = RoundedCornerShape(topStart = 8.dp,
                                    topEnd = 8.dp,
                                    bottomEnd = 0.dp,
                                    bottomStart = 8.dp),
                                color = Color.Blue
                            ) {
                                Text(
                                    text = message.content,
                                    color = Color.White,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    } else if (message.senderId == buddyId) {
                        // Incoming message bubble
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Surface(
                                shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    topEnd = 8.dp,
                                    bottomEnd = 8.dp,
                                    bottomStart = 0.dp),
                                color = Color.LightGray
                            ) {
                                Text(
                                    text = message.content,
                                    color = Color.Red,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }






            }



                if (isBuddy.value) {
                    Spacer(modifier = Modifier.weight(1f))
                    // Text field and send button
                    Column(verticalArrangement = Arrangement.Bottom) {
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(9.dp),
                            placeholder = { Text("Type a message") },
                            isError = false,
                            keyboardOptions = KeyboardOptions.Default,
                            keyboardActions = KeyboardActions.Default,
                            trailingIcon = {
                                IconButton(onClick = {
                                    if (userId != null) {
                                        if (buddyId != null) {
                                            viewModel.addMessage(userId, buddyId, message)
                                        }
                                    }
                                    message = ""  // Clear the text field
                                }) {
                                    Icon(Icons.Default.Send, contentDescription = "Send")
                                }
                            }
                        )
                    }
                } else {
                    AsyncImage(
                        model = "https://cdn.dribbble.com/users/1297837/screenshots/5467001/media/8177f477683c974835744ab8768a2a4a.gif",
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Still waiting for $fullName to accept your buddy request",
                        fontFamily = publicSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,)
                    
                }
                



        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopAppBar(navController: NavController,fullName: String?, viewModel: ChatViewModel = viewModel(),userId: String?,buddyId: String?){
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        modifier = Modifier.size(25.dp),
                        contentDescription = stringResource(R.string.sign_up_screen_back_button)
                    )
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 19.dp),
                    horizontalArrangement = Arrangement.Center,

                    ){
                    if (fullName != null) {
                        Text(
                            fullName,
                            fontFamily = publicSans,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }
        },
        actions = {
            IconButton(onClick = {
                if (userId != null) {
                    if (buddyId != null) {
                        viewModel.removeBuddy(userId, buddyId)

                        navController.navigate("start/$userId")
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    modifier = Modifier.size(25.dp),
                    contentDescription = "Block this user"
                )
            }
        }
    )


}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview(viewModel: ChatViewModel = viewModel()) {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        ChatScreen(navController,viewModel,"fdfaf","fdfaf","fdfaf","fdfaf")
    }
}
@Preview
@Composable
fun ChatScreenDarkPreview(viewModel: ChatViewModel = viewModel()) {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        ChatScreen(navController,viewModel,"fdfaf","fdfaf","fdfaf","fdfaf")
    }
}