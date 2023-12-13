package com.mad.tusmybuddyAMv1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherProfileScreen(navController: NavController,viewModel: UserProfileViewModel = viewModel(), userId: String?){
    val userDataState = userId?.let { viewModel.fetchUserData(it).collectAsState(initial = Pair(User(), 0)) }
    val userData = userDataState?.value ?: Pair(User(), 0)
    Scaffold(
        topBar = {OtherProfileScreenTopAppBar(navController)},


    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
                // buddies?.value?.let { MainScreenMessages(it, userId, navController) }
                // UserProfileMainScreen()
                userData.let { OtherProfileMainScreen(it) }


            }
            //Text(text = "User ID: $userId")

        }


    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OtherProfileMainScreen(userData: Pair<User, Int>){
    val user = userData.first
    val reputationIncrease = userData.second
    val buddiesCount = user.buddies?.filter { it != "placeholder" }?.size ?: 0
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = user.profilePicture,
            contentDescription = null,
            modifier = Modifier
                .size(103.dp)
                .clip(CircleShape)
                .clickable(onClick = {}),
            contentScale = ContentScale.Crop
        )

        //Profile Information
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start=11.dp,end =11.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "${user.fullName}",
                fontFamily = publicSans,
                fontSize =20.sp,
                fontWeight = FontWeight.SemiBold,

                )

        }
        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start=11.dp,end =11.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "${user.course}",
                fontFamily = publicSans,
                fontWeight = FontWeight.Light,
                fontSize =15.sp,

                )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start=11.dp,end =11.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "${user.bio}",
                fontFamily = publicSans,
                fontWeight = FontWeight.Light,
                fontSize =15.sp,
            )

        }
        Spacer(modifier = Modifier.height(18.dp))

        //Profile Stats
        Column(modifier = Modifier.fillMaxWidth()
            .padding(start=11.dp,end =11.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Row{
                Text(
                    text = "Buddies",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Year",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Reputation",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
            Row{
                Text(
                    text = "$buddiesCount",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${user.year}",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$reputationIncrease",
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        //Profile Hobbies skills and Interests
        Column {
            //Hobbies
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=11.dp,end =11.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hobbies",
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            //Hobbies
            FlowRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=3.dp,end =3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (user.hobbies != null) {
                    for (hobby in user.hobbies) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(3.dp)
                        ) {
                            Text(
                                text = " $hobby ",
                                color = Color.White,
                                fontFamily = publicSans,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                }


            }

            Spacer(modifier = Modifier.height(15.dp))

            //Skills

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=11.dp,end =11.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Skills",
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(7.dp))

            //Skills
            FlowRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=3.dp,end =3.dp),
                horizontalArrangement = Arrangement.Center
            )  {
                if (user.skills != null) {
                    for (skill in user.skills) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(3.dp)
                        ) {
                            Text(
                                text = " $skill ",
                                color = Color.White,
                                fontFamily = publicSans,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(15.dp))

            //Interests
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=11.dp,end =11.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Interests",
                    fontWeight = FontWeight.SemiBold,
                    fontSize =17.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(7.dp))

            //Interests
            FlowRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(start=3.dp,end =3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (user.interests != null) {
                    for (interest in user.interests) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(3.dp)
                        ) {
                            Text(
                                text = " $interest ",
                                color = Color.White,
                                fontFamily = publicSans,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }


            }
            Spacer(modifier = Modifier.height(15.dp))
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherProfileScreenTopAppBar(navController: NavController){
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically )
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        modifier = Modifier.size(25.dp),
                        contentDescription = stringResource(R.string.sign_up_screen_back_button)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))




            }
        },
        actions = {
        }


    )

}




@Preview(showBackground = true)
@Composable
fun OtherProfileScreenPreview(viewModel: UserProfileViewModel = viewModel()) {
    TUSMyBuddyTheme {
        val navController = rememberNavController()
        OtherProfileScreen(navController,viewModel, "dummy")
    }
}



@Preview
@Composable
fun OtherProfileScreenDarkPreview(viewModel: UserProfileViewModel = viewModel()) {
    TUSMyBuddyTheme(darkTheme = true) {
        val navController = rememberNavController()
        OtherProfileScreen(navController,viewModel,"dummy")
    }
}
