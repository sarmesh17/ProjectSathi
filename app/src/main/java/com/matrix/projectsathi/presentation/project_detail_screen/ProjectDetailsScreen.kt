package com.matrix.projectsathi.presentation.project_detail_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.matrix.projectsathi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()) // Allow scrolling for content
        ) {
            // Header Section
            ProjectHeader()

            Spacer(modifier = Modifier.height(16.dp))

            // Content Section
            ProjectContent(images = listOf(R.drawable.user_image, R.drawable.user_image))

            Spacer(modifier = Modifier.height(16.dp))

            ProjectAmountSection()



            Spacer(modifier = Modifier.height(16.dp))

            ProjectDetailsCard()
            Spacer(modifier = Modifier.height(16.dp))

            // Interaction Buttons
            InteractionButtons()
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

// Header Section
@Composable
fun ProjectHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.user_image),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))

        // Name and Role
        Column {
            Text("John Doe", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Software Engineer", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun ProjectContent(images: List<Int>) {
    Column {
        // Status Text
        Text(
            text = "This is a sample project description that explains the idea in detail."
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Image(s) Section
        if (images.size == 1) {
            // Single Image covers full width
            Image(
                painter = painterResource(id = images.first()),
                contentDescription = "Project Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            // Multiple Images in LazyRow with animation hint
            var showHint by remember { mutableStateOf(true) }

            Box {
                LazyRow {
                    items(images) { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Project Image",
                            modifier = Modifier
                                .width(300.dp)
                                .height(200.dp)
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                // Scroll Hint Animation
                if (showHint) {
                    val alphaAnim = remember { Animatable(1f) }

                    // Launch the animation
                    LaunchedEffect(Unit) {
                        alphaAnim.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 2000) // 2 seconds fade-out
                        )
                        showHint = false // Remove overlay after animation
                    }

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.3f)
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}


// Interaction Buttons
@Composable
fun InteractionButtons() {

    Button(
        onClick = { /* Handle Request to Join Action */ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(
                id = R.color.orange
            )
        ), modifier = Modifier
            .fillMaxWidth()
            .height(43.dp)
    ) {
        Text("Request to Join")
    }


}

@Composable
fun ProjectDetailsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Section: Skills Required
            Text(
                text = "Skills Required",
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Flutter, Kotlin, Firebase",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Section: Project Goal
            Text(
                text = "Project Goal",
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Build a cross-platform app for social networking.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Section: Collaborators
            Text(
                text = "Collaborators",
                style = MaterialTheme.typography.headlineSmall,
                color =  colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) {
                    Image(
                        painter = painterResource(id = R.drawable.user_image),
                        contentDescription = "Collaborator",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = CircleShape,
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Section: Technologies Used
            Text(
                text = "Technologies Used",
                style = MaterialTheme.typography.headlineSmall,
                color =  colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "• Kotlin Multiplatform\n• Jetpack Compose\n• Firebase\n• Material 3",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Section: Project Duration
            Text(
                text = "Project Duration",
                style = MaterialTheme.typography.headlineSmall,
                color =  colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Estimated Completion: 3 months",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Section: Client Available Time
            Text(
                text = "Client Available Time",
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Monday to Friday: 10 AM - 6 PM\nSaturday: 11 AM - 3 PM",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun ProjectAmountSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = colorResource(id = R.color.orange),
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.monetization_on),
                contentDescription = "Project Amount",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Project Amount: 50,000",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


