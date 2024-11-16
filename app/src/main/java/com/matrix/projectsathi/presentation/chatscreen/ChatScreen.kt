package com.matrix.projectsathi.presentation.chatscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.matrix.projectsathi.R

data class ChatMessage(val text: String, val isSentByUser: Boolean)

@Composable
fun ChatScreen(navHostController: NavHostController) {
    var messageList by remember { mutableStateOf<List<ChatMessage>>(emptyList()) }
    var messageText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { ChatTopBar(navHostController) },
        bottomBar = {
            ChatBottomBar(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendClick = {
                    if (messageText.isNotBlank()) {
                        // Add the user's message
                        messageList = messageList + ChatMessage(text = messageText, isSentByUser = true)

                        // Simulate bot response
                        if (messageText.equals("hi", ignoreCase = true)) {
                            messageList = messageList + ChatMessage(text = "Hello", isSentByUser = false)
                        }
                        messageText = ""
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (messageList.isEmpty()) {
                item {
                    Text(
                        text = "No chat history",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                items(messageList.size) { index ->
                    val message = messageList[index]
                    MessageBubble(
                        message = message.text,
                        isSentByUser = message.isSentByUser
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(navHostController: NavHostController) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Back action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Contact Name",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Online",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { /* Video call action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.video_call),
                    contentDescription = "Video Call", modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /* Voice call action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.call),
                    contentDescription = "Voice Call", modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /* More action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = "More", modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Composable
fun ChatBottomBar(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Emoji action */ }) {
            Icon(
                painter = painterResource(id = R.drawable.emoji),
                contentDescription = "Emoji", modifier = Modifier.size(24.dp)
            )
        }
        BasicTextField(
            value = messageText,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
            decorationBox = { innerTextField ->
                if (messageText.isEmpty()) {
                    Text(text = "Type a message", color = Color.Gray, fontSize = 16.sp)
                }
                innerTextField()
            }
        )
        IconButton(onClick = { /* Attach action */ }) {
            Icon(
                painter = painterResource(id = R.drawable.file),
                contentDescription = "Attach", modifier = Modifier.size(24.dp)
            )
        }
        IconButton(
            onClick = onSendClick,
            enabled = messageText.isNotBlank()
        ) {
            Icon(
                painter = painterResource(id = if (messageText.isNotBlank()) R.drawable.send else R.drawable.mic),
                contentDescription = if (messageText.isNotBlank()) "Send" else "Mic",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun MessageBubble(message: String, isSentByUser: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isSentByUser) colorResource(id = R.color.orange) else MaterialTheme.colorScheme.secondary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        ) {
            Text(
                text = message,
                color = if (isSentByUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

