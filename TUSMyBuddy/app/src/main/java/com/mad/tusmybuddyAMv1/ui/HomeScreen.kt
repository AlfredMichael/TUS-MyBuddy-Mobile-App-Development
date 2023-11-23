package com.mad.tusmybuddyAMv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mad.tusmybuddyAMv1.R
import com.mad.tusmybuddyAMv1.ui.theme.TUSMyBuddyTheme
import com.mad.tusmybuddyAMv1.ui.theme.publicSans

@Composable
fun HomeScreen(){
Column (modifier = Modifier
    .padding(dimensionResource(R.dimen.padding_home_columnA))
    .fillMaxSize()
    .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){
    //Image - TUS LOGO
    Column(modifier = Modifier.size(145.dp)) {
        Image(
            painter = painterResource(R.drawable.tus_logo_primary_eng_rgb),
            contentDescription = stringResource(R.string.home_screen_image_content_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .aspectRatio(1f)

        )

    }
    //Text
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            Row {
                Text(
                    text = stringResource(R.string.home_screen_text),
                    fontFamily = publicSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = stringResource(R.string.home_screen_textB),
                    fontFamily = publicSans,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 30.sp,

                )

            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Column() {
            Text(
                text = stringResource(R.string.home_screen_textMessage),
                fontFamily = publicSans,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight=FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
    //Login & Sign up Buttons
    Column() {
        Column {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.home_screen_loginButton),
                    fontFamily = publicSans,
                    fontWeight=FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.home_screen_registerButton),
                    fontFamily = publicSans,
                    fontWeight=FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TUSMyBuddyTheme {
        HomeScreen()
    }
}


@Preview
@Composable
fun HomeScreenDarkPreview() {
    TUSMyBuddyTheme(darkTheme = true) {
        HomeScreen()
    }
}