package com.mad.tusmybuddyAMv1.ui

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(){
    Scaffold(
        topBar = {StartScreenTopAppBar()}

    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {


            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreenTopAppBar(){
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
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    modifier = Modifier.size(25.dp),
                    contentDescription = stringResource(R.string.email_icon)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    modifier = Modifier.size(25.dp),
                    contentDescription = stringResource(R.string.user_icon)
                )
            }
        }
    )

}

@Composable
fun MainScreenMessages(){

}




@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    TUSMyBuddyTheme {
        StartScreen()
    }
}



@Preview
@Composable
fun StartScreenDarkPreview() {
    TUSMyBuddyTheme(darkTheme = true) {
        StartScreen()
    }
}