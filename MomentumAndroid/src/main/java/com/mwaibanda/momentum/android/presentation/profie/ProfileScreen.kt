package com.mwaibanda.momentum.android.presentation.profie

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwaibanda.momentum.android.core.utils.Constants
import com.mwaibanda.momentum.android.presentation.components.BasePlainExpandableCard
import com.mwaibanda.momentum.android.presentation.components.BottomSpacing
import com.mwaibanda.momentum.android.presentation.components.LinkLabel
import com.mwaibanda.momentum.android.presentation.components.TitleTextField
import com.mwaibanda.momentum.android.presentation.profie.ProfileViewModel.ProfileCard.*
import com.mwaibanda.momentum.utils.MultiplatformConstants
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = getViewModel()) {
    Box( ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Column {
                Column {
                    Spacer(modifier = Modifier.height(65.dp))
                    Text(
                        text = MultiplatformConstants.PROFILE,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(10.dp)
                    )
                    Divider()
                    Text(
                        text = MultiplatformConstants.PROFILE_SUBHEADING.uppercase(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color(Constants.MOMENTUM_ORANGE)
                    )
                }
                Row(
                    Modifier
                        .padding(10.dp)
                        .padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .clip(CircleShape)
                            .size(70.dp)
                            .border(
                                5.dp,
                                Color(Constants.MOMENTUM_ORANGE),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .clip(CircleShape)
                                .size(50.dp)
                                .background(Color(Constants.MOMENTUM_ORANGE))
                                .padding(5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Mwai Banda"
                                    .split(" ")
                                    .map { it.first().toString() }
                                    .reduce { x, y -> x + y },
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.h6,
                                color = Color.White
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Mwai Banda",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.h6,
                        )
                        Text(
                            text = "07/29/2019",
                            style = MaterialTheme.typography.caption,
                            color = Color.Gray
                        )
                    }
                }

                Column(

                ) {
                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isContactExpanded,
                        contentHeight = 315,
                        showCoverDivider = true,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.CONTACT_INFORMATION,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(CONTACT_INFO)
                            profileViewModel.closeCards(
                                BILLING_INFO,
                                MANAGE_ACC,
                                TECH_SUPPORT,
                                FEEDBACK,
                                INFORMATION
                            )
                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )

                        }
                    ) {
                        TitleTextField(title = MultiplatformConstants.FULLNAME, text = profileViewModel.fullname ){
                            profileViewModel.fullname = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.PHONE, text = profileViewModel.phone ){
                            profileViewModel.phone = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.EMAIL, text = profileViewModel.email ){
                            profileViewModel.email = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.PASSWORD, text = profileViewModel.password){
                            profileViewModel.password = it
                        }
                    }

                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isBillingExpanded,
                        contentHeight = 315,
                        showCoverDivider = profileViewModel.isContactExpanded,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Default.Domain, contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.BILLING_INFORMATION,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(BILLING_INFO)
                            profileViewModel.closeCards(
                                CONTACT_INFO,
                                MANAGE_ACC,
                                TECH_SUPPORT,
                                FEEDBACK,
                                INFORMATION
                            )

                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )

                        }
                    ) {
                        TitleTextField(title = MultiplatformConstants.STREET_ADDRESS, text = profileViewModel.streetAddress ){
                            profileViewModel.streetAddress = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.APT, text = profileViewModel.apt ){
                            profileViewModel.apt = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.CITY, text = profileViewModel.city ){
                            profileViewModel.city = it
                        }
                        Divider()
                        TitleTextField(title = MultiplatformConstants.ZIP_CODE, text = profileViewModel.zipCode){
                            profileViewModel.zipCode = it
                        }
                    }
                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isManageAccExpanded,
                        contentHeight = 230,
                        showCoverDivider = profileViewModel.isBillingExpanded,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Default.DeleteOutline,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.MANAGE_ACCOUNT,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(MANAGE_ACC)
                            profileViewModel.closeCards(
                                CONTACT_INFO,
                                BILLING_INFO,
                                TECH_SUPPORT,
                                FEEDBACK,
                                INFORMATION
                            )

                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )

                        }
                    ) {
                        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = MultiplatformConstants.DELETE_ACCOUNT,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(text = MultiplatformConstants.DELETION_WARNING, color = Color.Gray)
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(2.dp, Color(Constants.MOMENTUM_ORANGE))
                            ) {
                                Text(
                                    text = MultiplatformConstants.DELETE_ACCOUNT,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(Constants.MOMENTUM_ORANGE)
                                )
                            }
                        }
                    }
                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isTechSupportExpanded,
                        contentHeight = 260,
                        showCoverDivider = profileViewModel.isManageAccExpanded,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Default.SupervisorAccount,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.TECHNICAL_SUPPORT,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(TECH_SUPPORT)
                            profileViewModel.closeCards(
                                CONTACT_INFO,
                                MANAGE_ACC,
                                BILLING_INFO,
                                FEEDBACK,
                                INFORMATION
                            )

                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )

                        }
                    ) {
                        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = MultiplatformConstants.TECHNICAL_SUPPORT_PROMPT,
                                color = Color.Gray,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LinkLabel(title = MultiplatformConstants.TECHNICAL_SUPPORT, description = MultiplatformConstants.DEVELOPER_PHONE_TITLE) {

                            }
                            LinkLabel(title = MultiplatformConstants.TECHNICAL_SUPPORT, description = MultiplatformConstants.DEVELOPER_EMAIL_TITLE) {

                            }
                        }
                    }
                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isFeedbackExpanded,
                        contentHeight = 190,
                        showCoverDivider = profileViewModel.isTechSupportExpanded,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Outlined.Chat, contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.FEEDBACK,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(FEEDBACK)
                            profileViewModel.closeCards(
                                CONTACT_INFO,
                                MANAGE_ACC,
                                TECH_SUPPORT,
                                BILLING_INFO,
                                INFORMATION
                            )

                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                        }
                    ) {
                        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = MultiplatformConstants.FEEDBACK_PROMPT,
                                color = Color.Gray,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LinkLabel(
                                title = MultiplatformConstants.FEEDBACK,
                                description = MultiplatformConstants.DEVELOPER
                            ) {

                            }
                        }
                    }
                    BasePlainExpandableCard(
                        isExpanded = profileViewModel.isInformationExpanded,
                        contentHeight = 445,
                        showCoverDivider = profileViewModel.isFeedbackExpanded,
                        coverContent = {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = MultiplatformConstants.INFORMATION,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold)
                            )
                        },
                        onCoverClick = {
                            profileViewModel.cardToggle(INFORMATION)
                            profileViewModel.closeCards(
                                CONTACT_INFO,
                                MANAGE_ACC,
                                TECH_SUPPORT,
                                FEEDBACK,
                                BILLING_INFO
                            )

                        },
                        coverIcon = { isExpanded ->
                            Icon(
                                imageVector = if (isExpanded)
                                    Icons.Default.ArrowDropUp
                                else
                                    Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Icon",
                                tint = Color.Gray
                            )

                        }
                    ) {
                        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = MultiplatformConstants.ABOUT_CHURCH,
                                color = Color.Gray,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LinkLabel(
                                title = MultiplatformConstants.MOMENTUM_PHONE,
                                description = MultiplatformConstants.CHURCH_PHONE_TITLE
                            ) {

                            }
                            LinkLabel(
                                title = MultiplatformConstants.MOMENTUM_PHONE,
                                description = MultiplatformConstants.CHURCH_EMERGENCY_PHONE_TITLE
                            ) {

                            }
                            LinkLabel(
                                title = MultiplatformConstants.MOMENTUM_EMAIL,
                                description = MultiplatformConstants.CHURCH_EMAIL_TITLE
                            ) {

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = MultiplatformConstants.COPYRIGHT,
                                color = Color.Gray,
                                style = MaterialTheme.typography.caption
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        Divider()
                    }
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
            BottomSpacing()
        }
        Column(
            Modifier
                .fillMaxSize()
                .offset(y = 4.dp), verticalArrangement = Arrangement.Bottom) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(55.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE55F1F))
                ) {
                    Text(
                        text = "Sign Out",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
            }
            BottomSpacing()
        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(ProfileViewModel())
}