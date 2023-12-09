package com.mad.tusmybuddyAMv1.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans

@Composable
fun ProfileScreen(navController: NavController, userId: String?){
    // Get an instance of ProfileViewModel
    val viewModel: ProfileViewModel = viewModel()
    // Fetch user data when the ProfileScreen is composed
    LaunchedEffect(userId) {
        userId?.let { viewModel.fetchUserData(it) }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        //Header
        ProfileScreenMainHeader(navController)
        //End Of Header

        //Main Content
        var profilePicture by remember { mutableStateOf<Uri?>(null) } // Store the image URI
        /*val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri ->
            profilePicture = uri
        }*/

        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri ->
            profilePicture = uri
            userId?.let {
                if (uri != null) {
                    viewModel.uploadImage(it, uri)
                }
            }
        }

        ProfileScreenMainContent(userId, profilePicture, navController, viewModel){
            launcher.launch("image/*")
        }
        //End of Main Content



        //End Of Button
        // Test - Display the user ID
        //Text(text = "User ID: $userId")


    }


}

//Header - {Profile, X Icon}
@Composable
fun ProfileScreenMainHeader(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(R.dimen.padding_profile_screen_start),
            end = dimensionResource(R.dimen.padding_profile_screen_end),
            top = dimensionResource(R.dimen.padding_profile_screen_top)

        )){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.padding_profile_screen_row_header_start),
                    end = dimensionResource(R.dimen.padding_profile_screen_row_header_end)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Profile Text
            Text(
                text = stringResource(R.string.profile_screen_header_text),
                fontFamily = publicSans,
                fontWeight= FontWeight.Normal,
                fontSize = 19.sp,
            )

            //X icon button
            IconButton(onClick = {navController.navigate(Screen.Home.route)}){
                Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(R.string.profile_screen_close_icon))
            }

        }
    }
}

//Main Content - Card with profile
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenMainContent(
    userId: String?,
    profilePicture: Uri? = null,
    navController: NavController,
    viewModel: ProfileViewModel,
    onImageClick: ()-> Unit
){
    var username by remember{mutableStateOf("")}
    var bio by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var hobbies by remember { mutableStateOf("") }
    var interests by remember { mutableStateOf("") }

    // Observe the userData LiveData
    val userData by viewModel.userData.observeAsState()

    val errorMessage by viewModel.errorMessage.observeAsState()

    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.errorMessage.value = null },
            title = { Text("Error") },
            text = { Text(text = errorMessage!!) },
            confirmButton = {
                Button(onClick = { viewModel.errorMessage.value = null }) {
                    Text("OK")
                }
            }
        )
    }


    Column(modifier = Modifier.padding(
        start = dimensionResource(R.dimen.profile_screen_column_card_padding_start),
        end = dimensionResource(R.dimen.profile_screen_column_card_padding_end)
    ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Card
        Card(modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.profile_screen_card_padding_start),
                    end = dimensionResource(R.dimen.profile_screen_card_padding_end),
                    top = dimensionResource(R.dimen.profile_screen_card_padding_top),
                    bottom = dimensionResource(R.dimen.profile_screen_card_padding_bottom)
                ),
            horizontalAlignment = Alignment.CenterHorizontally){
                //Image
                if(profilePicture != null)
                    Image(
                        painter = rememberImagePainter(profilePicture),
                        contentDescription = stringResource(R.string.profile_screen_upload_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.profile_screen_profile_picture_size))
                            .clip(CircleShape)
                            .clickable { onImageClick() }

                    )
                else
                    Image(
                        painter = painterResource(R.drawable.uploadprofiletus),
                        contentDescription = stringResource(R.string.profile_screen_upload_image),
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.profile_screen_profile_picture_size))
                            .clickable { onImageClick() }
                    )

                //Text - FullName
                Text(
                    text = " ${userData?.fullName}",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )

                //Text - Course
                Text(
                    text = " ${userData?.course}",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )

                //Text - Year
                Text(
                    text = "Year ${userData?.year}",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )

                //Input fields - Username, Bio, Interests, Skills and Hobbies
                OutlinedTextField(
                    value = username,
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {username=it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        disabledBorderColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = { Text(
                        stringResource(R.string.profile_screen_username_label),
                        fontFamily = publicSans,
                        fontWeight=FontWeight.Normal,
                        fontSize = 15.sp,) },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                )
                //Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = bio,
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {bio=it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        disabledBorderColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = { Text(
                        stringResource(R.string.profile_screen_bio_label),
                        fontFamily = publicSans,
                        fontWeight=FontWeight.Normal,
                        fontSize = 15.sp,) },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                )

                //Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = skills,
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {skills=it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        disabledBorderColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = { Text(
                        stringResource(R.string.profile_screen_skills_label),
                        fontFamily = publicSans,
                        fontWeight=FontWeight.Normal,
                        fontSize = 15.sp,) },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                )

                //Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = interests,
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {interests=it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        disabledBorderColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = { Text(
                        stringResource(R.string.profile_screen_interests_label),
                        fontFamily = publicSans,
                        fontWeight=FontWeight.Normal,
                        fontSize = 15.sp,) },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                )

                //Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = hobbies,
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {hobbies=it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        disabledBorderColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = { Text(
                        stringResource(R.string.profile_screen_hobbies_label),
                        fontFamily = publicSans,
                        fontWeight=FontWeight.Normal,
                        fontSize = 15.sp,) },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                )

                Column(modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_profile_screen_button_start),
                    end = dimensionResource(R.dimen.padding_profile_screen_button_end),
                    top = dimensionResource(R.dimen.padding_profile_screen_button_top)

                )){
                    //Button
                    Button(
                        onClick = { userId?.let {
                            viewModel.updateUserData(it, username, bio, skills, hobbies, interests)

                            //Navigate to the start screen
                            navController.navigate("start/${it}")
                        } },
                        shape= MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.profile_screen_create_profile_button),
                            fontFamily = publicSans,
                            fontWeight=FontWeight.Normal,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        ProfileScreen(navController,"dummmmy")
    }
}

@Preview
@Composable
fun ProfileScreenDarkPreview() {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        ProfileScreen(navController,"dummmmy")
    }
}