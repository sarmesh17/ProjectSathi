package com.matrix.projectsathi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dummy Data Class
data class FriendRequest(
    val id: Int,
    val name: String,
    val jobTitle: String,
    val profileImage: Int // Replace with your image resource
)

// Sample Friend Requests
val sampleRequests = listOf(
    FriendRequest(1, "Ram Hari", "Product Manager at Google", R.drawable.profile_filled),
    FriendRequest(2, "Alice", "Senior Developer at Microsoft", R.drawable.profile_filled),
    FriendRequest(3, "Raman", "UX Designer at Adobe", R.drawable.profile_filled)
)

@Composable
fun RequestScreen() {
    var requests by remember { mutableStateOf(sampleRequests) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Requests",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        requests.forEach { request ->
            RequestItem(
                request = request,
                onAccept = {
                    requests = requests.filter { it.id != request.id }
                },
                onDecline = {
                    requests = requests.filter { it.id != request.id }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RequestItem(
    request: FriendRequest,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = request.profileImage),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .padding(8.dp)
        )

        // Name and Job Title
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(text = request.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = request.jobTitle, fontSize = 14.sp, color = Color.Gray)
        }

        // Accept Button
        Button(
            onClick = onAccept,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .height(40.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Accept")
        }

        // Decline Button
        OutlinedButton(
            onClick = onDecline,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .height(40.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Decline")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RequestScreenPreview() {
    RequestScreen()
}
