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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morozov.testtaskforviesure.R
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.ui.theme.AppDimens
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import com.morozov.testtaskforviesure.ui.theme.BlackMain
import com.morozov.testtaskforviesure.ui.theme.Grey
import com.morozov.testtaskforviesure.ui.theme.Purple40
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
                customTitleView = { Text(stringResource(R.string.about_me)) },
                showBackIcon = true,
                isTransparent = true,
            )
        )
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(AppDimens.basesSpacingsSpacingLg16)
            .background(BlackMain)
    ) {
        stickyHeader {
            Header()
            Spacer(modifier = Modifier.height(AppDimens.basesSpacingsSpacingLg16))
        }


        item {
            WorkExperience()
            Spacer(modifier = Modifier.height(AppDimens.basesSpacingsSpacingLg16))
        }
        item {
            Education()
            Spacer(modifier = Modifier.height(AppDimens.basesSpacingsSpacingLg16))
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
        Spacer(modifier = Modifier.width(AppDimens.basesSpacingsSpacingLg16))
        Column {
            Text(text = "OLEKSANDR MOROZOV", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Android Software Engineer", fontSize = 16.sp)
        }
    }
}

@Composable
fun WorkExperience() {
    Section(title = stringResource(R.string.work_experience)) {
        WorkExperienceItem(
            role = stringResource(R.string.software_engineer),
            company = stringResource(R.string.global_logic),
            date = stringResource(R.string.dec_2022_present),
            description = stringResource(R.string.key_role_in_a_5_member)
        )
        WorkExperienceItem(
            role = stringResource(R.string.junior_software_engineer),
            company = stringResource(R.string.global_logic),
            date = stringResource(R.string.sep_2021_dec_2022),
            description = stringResource(R.string.key_role_in_a_3_member)
        )
        WorkExperienceItem(
            role = stringResource(R.string.associate_software_engineer),
            company = stringResource(R.string.global_logic),
            date = stringResource(R.string.oct_2021_sep_2022),
            description = stringResource(R.string.leveraged_java_and_kotlin_)
        )
        WorkExperienceItem(
            role = stringResource(R.string.trainee_software_engineer),
            company = stringResource(R.string.global_logic),
            date = stringResource(R.string.jun_2021_sep_2021),
            description = stringResource(R.string.gained_comprehensive_training)
        )
    }
}

@Composable
fun WorkExperienceItem(role: String, company: String, date: String, description: String) {
    Column(modifier = Modifier.padding(bottom = AppDimens.basesSpacingsSpacingSm8)) {
        Text(text = role, fontWeight = FontWeight.Bold)
        Text(text = company, style = AppTypography.sub2)
        Text(text = date, style = AppTypography.sub2)
        Text(text = description, style = AppTypography.sub2)
    }
}

@Composable
fun Education() {
    Section(title = stringResource(R.string.education)) {
        Text(text = stringResource(R.string.quickly_absorbed), style = AppTypography.sub2)
    }
}

@Composable
fun Skills() {
    Section(title = stringResource(R.string.skills)) {
        Text(text = stringResource(R.string.analytical_thinking), style = AppTypography.sub2)
    }
}

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Purple40)
        Spacer(modifier = Modifier.height(AppDimens.basesSpacingsSpacingSm8))
        content()
    }
}

