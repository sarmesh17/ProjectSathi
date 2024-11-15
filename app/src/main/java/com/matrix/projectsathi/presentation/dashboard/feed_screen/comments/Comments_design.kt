package com.matrix.projectsathi.presentation.dashboard.feed_screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matrix.projectsathi.R

@Composable
@Preview(showSystemUi = true)
fun ChatDesign(
    comments: List<String> = listOf("Hello", "Hi, how are you?"),
    onAddComment: (String) -> Unit = {}
) {
    var commentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        comments.forEach { comment ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Comment Text
                Column(
                    modifier = Modifier
                        .background(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "User Name",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(3.dp)
                    )
                    Text(
                        text = comment,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

        // Add Comment Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // TextField for Comments
            TextField(
                value = commentText,
                onValueChange = { commentText = it },
                placeholder = { Text("Write a message...") },
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 8.dp).clip(shape = RoundedCornerShape(12.dp)),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF0F0F0),
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (commentText.isNotBlank()) {
                        onAddComment(commentText)
                        commentText = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Send")
            }
        }
    }
}
