package com.morozov.testtaskforviesure.ui.screens.aboutMe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morozov.testtaskforviesure.R
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import com.morozov.testtaskforviesure.ui.theme.BlackMain
import com.morozov.testtaskforviesure.ui.theme.Grey
import com.morozov.testtaskforviesure.utils.clickableNoRipple


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutMe(
    viewModel: AboutMeViewModel
) {

    val topBarStateUpdater = LocalTopBarUpdater.current

    LaunchedEffect(Unit) {
        topBarStateUpdater(
            TopBarState(
                customBackView = {
                    Icon(
                        modifier = Modifier
                            .clickableNoRipple { viewModel.send(AboutMeAction.GoBack) },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Grey // Set the desired color here
                    )
                },
                isCenterAligned = true,
                showTitle = true,
                customTitleView = { Text("About me") },
                showBackIcon = true,
                isTransparent = true,
            )
        )
    }



    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(16.dp)
            .background(BlackMain)
    ) {
        stickyHeader {
            Header()
            Spacer(modifier = Modifier.height(16.dp))
        }


       item {   WorkExperience()
        Spacer(modifier = Modifier.height(16.dp))}
        item {
            Education()
            Spacer(modifier = Modifier.height(16.dp))
        }
      item {
          Skills()
      }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.background(BlackMain),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.picture_about_me),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "OLEKSANDR MOROZOV", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Android Software Engineer", fontSize = 16.sp)
        }
    }
}

@Composable
fun WorkExperience() {
    Section(title = "WORK EXPERIENCE") {
        WorkExperienceItem(
            role = "Software Engineer",
            company = "GLOBALLOGIC",
            date = "Dec 2022 - Present",
            description = "Key role in a 6-member Android development team\nLead initiatives in Kotlin and Jetpack Compose (Daily Wire application)\nExpertise in MVVM, Jetpack Compose, coroutines, Kettle flow, Hilt"
        )
        WorkExperienceItem(
            role = "Junior Software Engineer",
            company = "GLOBALLOGIC",
            date = "Sep 2021 - Dec 2022",
            description = "Key role in a 5-member Android development team\nLead initiatives in Kotlin and Jetpack Compose (Bank Wire application)\nExpertise in MVVM, Jetpack Compose, coroutines, Kettle flow, Hilt"
        )
        WorkExperienceItem(
            role = "Associate Software Engineer",
            company = "GLOBALLOGIC",
            date = "Oct 2021 - Sep 2022",
            description = "Leveraged Java and Kotlin in crafting top-tier sports and news apps\nSignificantly improved app functionality using Dagger\nAdvanced the digital transformation of a leading brand's sports channel"
        )
        WorkExperienceItem(
            role = "Trainee Software Engineer",
            company = "GLOBALLOGIC UKRAINE",
            date = "Jun 2021 - Sep 2021",
            description = "Gained comprehensive training in Android development\nApplied advanced knowledge to Android app design and application\nContributed to the growth of Android development projects"
        )
    }
}

@Composable
fun WorkExperienceItem(role: String, company: String, date: String, description: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = role, fontWeight = FontWeight.Bold)
        Text(text = company, style = AppTypography.sub2)
        Text(text = date, style = AppTypography.sub2)
        Text(text = description,  style = AppTypography.sub2)
    }
}

@Composable
fun Education() {
    Section(title = "EDUCATION") {
        Text(text = "Quickly absorbed Javascript base fundamentals at Softserve, Nov 2018\nDemonstrated proficiency in English after studying at American English Center, Dec 2018\nEnrolled in JAVA DEV, JAVA core course, refining coding skills and methodologies, Mar 2019\nCompleted ADU implementation of automation testing course from ZOLOV and ITEA, earning high scores, Mar 2020-Mar 2021\nAndroid Kotlin course - EPAM April-June 2021", style = AppTypography.sub2)
    }
}

@Composable
fun Skills() {
    Section(title = "SKILLS") {
        Text(text = "Analytical Thinking, Java Programming Language, Firebase, Jetpack Compose, Kotlin, Adaptability, Android Operating System, Leadership, Coroutines, HILT", style = AppTypography.sub2)
    }
}

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EA))
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

