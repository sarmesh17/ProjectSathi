package com.matrix.projectsathi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareThoughtScreen(
    profileImageUrl: Int,
    onPostClick: (String) -> Unit
) {
    var thoughtText by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var visibilityOption by remember { mutableStateOf("Anyone") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar with Close Icon, Profile Image, Visibility Option, and Post Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Close Icon
            IconButton(onClick = { /* Handle Close */ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }

            // Profile Image and Visibility Option
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Profile Image
                Image(
                    painter = rememberAsyncImagePainter(profileImageUrl),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Visibility Dropdown
                Box (
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            isDropdownExpanded = true
                        }
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = visibilityOption,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Dropdown Menu
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Anyone") },
                            onClick = {
                                visibilityOption = "Anyone"
                                isDropdownExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Connections") },
                            onClick = {
                                visibilityOption = "Connections"
                                isDropdownExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Only Me") },
                            onClick = {
                                visibilityOption = "Only Me"
                                isDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            // Post Button
            Button(
                onClick = { onPostClick(thoughtText) },
                enabled = thoughtText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                     if (thoughtText.isNotBlank()) MaterialTheme.colorScheme.primary else Color.Gray
                )
            ) {
                Text(text = "Post")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Text Field for Thoughts
        BasicTextField(
            value = thoughtText,
            onValueChange = { thoughtText = it },
            //placeholder = { Text(text = "Share your thoughts...") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            /*colors = TextFieldDefaults.textFieldColors(
                 Color.Black
            )*/
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (thoughtText.isEmpty()) {
                        Text(
                            text = "Share your thoughts...",
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Actions (Add Image, Add More)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /* Handle Add Image */ }) {
                Icon(painter = painterResource(id = R.drawable.image), contentDescription = null, modifier = Modifier.size(27.dp))
            }
            IconButton(onClick = { /* Handle Add More Options */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add More",
                    tint = Color.Black
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ShareThoughtScreenPreview() {
    ShareThoughtScreen(
        profileImageUrl = R.drawable.img,
        onPostClick = { thoughtText ->
            println("User shared: $thoughtText")
        }
    )
}

