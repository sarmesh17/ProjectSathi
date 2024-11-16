package com.matrix.projectsathi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    profileImageUrl: Int,
    userName: String,
    headline: String,
    summary: String,
    experienceList: List<Experience>,
    educationList: List<Education>,
    skillsList: List<String>,
    certificationsList: List<Certification>
) {
    var linkedin by remember {
        mutableStateOf("")
    }
    var twitter by remember {
        mutableStateOf("")
    }
    var facebook by remember {
        mutableStateOf("")
    }
    var youtube by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Profile Image and Edit Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = rememberAsyncImagePainter(profileImageUrl),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            IconButton(
                onClick = { /* Edit Profile Image */ },
                modifier = Modifier.padding(start = 300.dp, top = 60.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Profile Image")
            }
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // User Name and Headline
        Text(text = userName, style = MaterialTheme.typography.headlineMedium)
        Text(text = headline, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        // Summary Section
        Text(text = "Summary", style = MaterialTheme.typography.titleMedium)
        Text(text = summary, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Experience Section
        Text(text = "Experience", style = MaterialTheme.typography.titleMedium)
        experienceList.forEach { experience ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // Company Logo
                Image(
                    painter = rememberAsyncImagePainter(experience.companyLogoUrl),
                    contentDescription = "Company Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Job Title, Company Name, and Description
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = experience.jobTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = experience.companyName,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = experience.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = experience.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Divider()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Education Section
        Text(text = "Education", style = MaterialTheme.typography.titleMedium)
        educationList.forEach { education ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // Institution Logo
                Image(
                    painter = rememberAsyncImagePainter(education.institutionLogoUrl),
                    contentDescription = "Institution Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Education Details
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${education.degree} in ${education.fieldOfStudy}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = education.institutionName,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = "${education.startDate} - ${education.endDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    // Optional Description
                    education.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Divider()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Skills Section
        Text(text = "Skills", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            //mainAxisSpacing = 8.dp,
            //crossAxisSpacing = 8.dp
        ) {
            skillsList.forEach { skill ->
                Chip(
                    onClick = { /* Handle click */ },
                    label = { Text(text = skill, color = Color.White) },
                    colors = ChipDefaults.primaryChipColors(colorResource(id = R.color.amber)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        // Licenses & Certifications
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Licenses & Certifications",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            certificationsList.forEach { certification ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    // Certification Logo
                    Image(
                        painter = rememberAsyncImagePainter(certification.logoUrl),
                        contentDescription = "Certification Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Certification Details
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = certification.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Issued by ${certification.issuingOrganization}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = "Issued on ${certification.issueDate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )

                        // Optional Credential ID
                        certification.credentialId?.let {
                            Text(
                                text = "Credential ID: $it",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        // Optional Credential URL
                        certification.credentialUrl?.let { url ->
                            Text(
                                text = "View Credential",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.clickable { /* Open URL */ }
                            )
                        }
                    }
                }

                Divider()
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // row for linkedin and twitter
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                //linkedIn
                Column {
                    Text(
                        text = "LinkedIn:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .height(32.dp)
                            .width(160.dp)
                    ) {
                        BasicTextField(
                            value = linkedin,
                            onValueChange = { linkedin = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(fontSize = 20.sp),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    if (linkedin.isEmpty()) {
                                        Text(
                                            text = "LinkedIn Profile URL",
                                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }

                //twitter
                Column {
                    Text(
                        text = "Twitter:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .height(32.dp)
                            .width(160.dp)
                    ) {
                        BasicTextField(
                            value = twitter,
                            onValueChange = { twitter = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(fontSize = 20.sp,),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    if (twitter.isEmpty()) {
                                        Text(
                                            text = "Twitter Profile URL",
                                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // row for facebook and youtube
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                //facebook
                Column {
                    Text(
                        text = "Facebook:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .height(32.dp)
                            .width(160.dp)
                    ) {
                        BasicTextField(
                            value = facebook,
                            onValueChange = { facebook = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(fontSize = 20.sp),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    if (facebook.isEmpty()) {
                                        Text(
                                            text = "Facebook Profile URL",
                                            style = TextStyle(fontSize = 15.sp, color = Color.Gray)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }

                //youtube
                Column {
                    Text(
                        text = "YouTube:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .height(32.dp)
                            .width(160.dp)
                    ) {
                        BasicTextField(
                            value = youtube,
                            onValueChange = { youtube = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(fontSize = 20.sp),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    if (youtube.isEmpty()) {
                                        Text(
                                            text = "YouTube Channel URL",
                                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        profileImageUrl = R.drawable.img,
        userName = "John Doe",
        headline = "Senior Android Developer at XYZ Corp",
        summary = "Passionate about building scalable mobile applications...",
        experienceList = listOf(
            Experience(
                companyLogoUrl = R.drawable.img,
                jobTitle = "Senior Developer",
                companyName = "XYZ Corp",
                duration = "Jan 2020 - Present",
                description = "Leading the mobile team and building scalable Android applications."
            ),
            Experience(
                companyLogoUrl = R.drawable.img,
                jobTitle = "Android Developer",
                companyName = "ABC Inc.",
                duration = "Jul 2018 - Dec 2019",
                description = "Developed key features for the company's flagship app, increasing user engagement."
            )
        ),
        educationList = listOf(
            Education(
                institutionLogoUrl = R.drawable.img,
                degree = "Bachelor of Computer Science",
                fieldOfStudy = "Software Engineering",
                institutionName = "University of Tech",
                startDate = "Sep 2015",
                endDate = "Jun 2019",
                description = "Focused on software development and data structures, graduated with honors."
            ),
            Education(
                institutionLogoUrl = R.drawable.img,
                degree = "Master of Software Engineering",
                fieldOfStudy = "Mobile Development",
                institutionName = "ABC University",
                startDate = "Sep 2019",
                endDate = "Jun 2021",
                description = "Specialized in mobile application development using Android and iOS frameworks."
            )
        ),
        skillsList = listOf("Kotlin", "Jetpack Compose", "Firebase", "Dagger Hilt"),
        certificationsList = listOf(
            Certification(
                logoUrl = "https://example.com/cert_logo1.png",
                name = "Google Associate Android Developer",
                issuingOrganization = "Google",
                issueDate = "May 2021",
                credentialId = "123-456-789",
                credentialUrl = "https://example.com/credential"
            ),
            Certification(
                logoUrl = "https://example.com/cert_logo2.png",
                name = "AWS Certified Solutions Architect",
                issuingOrganization = "Amazon Web Services",
                issueDate = "June 2022",
                credentialId = null,
                credentialUrl = "https://example.com/aws-credential"
            )
        )
    )
}

data class Experience(
    val companyLogoUrl: Int,
    val jobTitle: String,
    val companyName: String,
    val duration: String,
    val description: String
)

data class Certification(
    val logoUrl: String,
    val name: String,
    val issuingOrganization: String,
    val issueDate: String,
    val credentialId: String?,
    val credentialUrl: String?
)

data class Education(
    val institutionLogoUrl: Int,
    val degree: String,
    val fieldOfStudy: String,
    val institutionName: String,
    val startDate: String,
    val endDate: String,
    val description: String?
)




