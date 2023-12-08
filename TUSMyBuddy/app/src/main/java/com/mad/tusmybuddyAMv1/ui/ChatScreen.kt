package com.mad.tusmybuddyAMv1.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController){
    var message by remember { mutableStateOf("") }
    Scaffold(
        topBar = {ChatScreenTopAppBar(navController)}

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxSize()) {

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

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
                            text = "Hey, Fran How are you?",
                            //color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


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
                            text = "Hi Michael",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }




            }
            Spacer(modifier = Modifier.weight(1f))

            Column(verticalArrangement = Arrangement.Bottom) {
                // Text field and send button
                OutlinedTextField(
                    value = message,
                    onValueChange = {message=it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(9.dp),
                    placeholder = { Text("Type a message") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                    trailingIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                    }
                )

            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopAppBar(navController: NavController){
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
                    Text(
                        "Alfred Michael",
                        fontFamily = publicSans,
                        fontWeight = FontWeight.Bold
                    )
                }


            }
        },
        actions = {
        }
    )


}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        ChatScreen(navController)
    }
}
@Preview
@Composable
fun ChatScreenDarkPreview() {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        ChatScreen(navController)
    }
}