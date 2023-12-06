@file:OptIn(ExperimentalMaterial3Api::class)
package com.mad.tusmybuddyAMv1.ui

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(){
    Scaffold(
        topBar = {StartScreenTopAppBar()},
        bottomBar = {BottomNavigationBar()}


    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {

                MainScreenMessages()

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
                    imageVector = Icons.Filled.Call,
                    modifier = Modifier.size(25.dp),
                    contentDescription = stringResource(R.string.email_icon)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    modifier = Modifier.size(25.dp),
                    contentDescription = stringResource(R.string.user_icon)
                )
            }
        }

    )

}

@Composable
fun MainScreenMessages(){
    Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp, top = 12.dp, bottom = 3.dp)
                .clickable(onClick = { }),
    shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://firebasestorage.googleapis.com/v0/b/tus-mybuddy.appspot.com/o/profilePictures%2FsY1MGkf4SsgNxixt2cqqsChOpCD2?alt=media&token=e51e4f5b-30bb-4e4e-b129-e9478f6696b9",
                contentDescription = null,
                modifier = Modifier
                    .size(53.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {})
            )


            Text(
                text = "Alfred Michael",
                fontFamily = publicSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 12.dp, bottom = 3.dp)
            .clickable(onClick = { }),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://firebasestorage.googleapis.com/v0/b/tus-mybuddy.appspot.com/o/profilePictures%2FsY1MGkf4SsgNxixt2cqqsChOpCD2?alt=media&token=e51e4f5b-30bb-4e4e-b129-e9478f6696b9",
                contentDescription = null,
                modifier = Modifier
                    .size(53.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {})
            )


            Text(
                text = "Alfred Michael",
                fontFamily = publicSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 12.dp, bottom = 3.dp)
            .clickable(onClick = { }),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://firebasestorage.googleapis.com/v0/b/tus-mybuddy.appspot.com/o/profilePictures%2FsY1MGkf4SsgNxixt2cqqsChOpCD2?alt=media&token=e51e4f5b-30bb-4e4e-b129-e9478f6696b9",
                contentDescription = null,
                modifier = Modifier
                    .size(53.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {})
            )


            Text(
                text = "Alfred Michael",
                fontFamily = publicSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 12.dp, bottom = 3.dp)
            .clickable(onClick = { }),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://firebasestorage.googleapis.com/v0/b/tus-mybuddy.appspot.com/o/profilePictures%2FsY1MGkf4SsgNxixt2cqqsChOpCD2?alt=media&token=e51e4f5b-30bb-4e4e-b129-e9478f6696b9",
                contentDescription = null,
                modifier = Modifier
                    .size(53.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {})
            )


            Text(
                text = "Alfred Michael",
                fontFamily = publicSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { /* do something */ }) {
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
            IconButton(onClick = { /* do something */ }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.Person, contentDescription = "User Icon")
                    Text(text = "Profile", fontSize = 12.sp)
                }
            }
            IconButton(onClick = { /* do something */ }) {
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