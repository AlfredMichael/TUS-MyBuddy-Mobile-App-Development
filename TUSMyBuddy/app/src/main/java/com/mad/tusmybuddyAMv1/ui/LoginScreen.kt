package com.mad.tusmybuddyAMv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()){
    val errorMessage by viewModel.errorMessage.observeAsState()
    Scaffold(
        topBar = {LoginTopAppBar(navController)}

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
                if (errorMessage != null) {
                    AlertDialog(
                        onDismissRequest = { viewModel.errorMessage.value = null },
                        title = { Text("") },
                        text = {
                            Column {
                                Image(
                                    painter = painterResource(R.drawable.tus_logo_primary_eng_rgb),
                                    contentDescription = "Dialog Image",
                                    modifier = Modifier.size(100.dp)
                                )
                                Text("Error", fontFamily = publicSans,fontWeight = FontWeight.Bold,fontSize = 22.sp)
                                Text(text = errorMessage!!, fontFamily = publicSans)
                            }
                               },
                        confirmButton = {
                            TextButton(onClick = { viewModel.errorMessage.value = null }) {
                                Text(text = "OK")
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                LoginTopText()
                LoginMainContent(navController,viewModel)

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar(navController: NavController){
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically )
            {
                IconButton(onClick = { navController.popBackStack() }) {

                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,modifier = Modifier.size(25.dp), contentDescription = stringResource(
                        R.string.sign_up_screen_back_button)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    stringResource(R.string.home_screen_text) + stringResource(R.string.home_screen_textB),
                    fontFamily = publicSans, fontWeight = FontWeight.Bold )


            }
        },
        actions = {
        }


    )

}

@Composable
fun LoginTopText(){
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.sign_in_screen_text),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = publicSans,
            fontWeight=FontWeight.Normal,
            fontSize = 18.sp,)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMainContent(navController: NavController, viewModel: LoginViewModel){
    //Input fields
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observe the userId LiveData
    val userId = viewModel.userId.observeAsState()
    Column(modifier = Modifier
        .padding(
            start= dimensionResource(R.dimen.padding_input_fields_start),
            end = dimensionResource(R.dimen.padding_input_fields_end),
            top = dimensionResource(R.dimen.padding_input_fields_top),
            bottom = dimensionResource(R.dimen.padding_input_fields_bottom)
        )){
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = email,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {email=it},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
            ),
            label = { Text(
                stringResource(R.string.sign_up_screen_email),
                fontFamily = publicSans,
                fontWeight=FontWeight.Normal,
                fontSize = 18.sp,) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {password = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
            ),
            label = { Text(stringResource(R.string.sign_up_screen_password)) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(15.dp))

    }
    //Button
    Column(modifier = Modifier
        .padding(
            start = dimensionResource(R.dimen.padding_sign_up_button_start),
            end = dimensionResource(R.dimen.padding_sign_up_button_end)
        )){
        Button(
            onClick = {  viewModel.loginUser(email.lowercase(Locale.getDefault()), password)

                // Navigate to the Profile screen if the userId is not null
                if (userId.value != null) {
                    navController.navigate("start/${userId.value}")
                } },
            shape= MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.sign_in_screen_signIn_button),
                fontFamily = publicSans,
                fontWeight=FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}

@Preview
@Composable
fun LoginScreenDarkPreview() {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}