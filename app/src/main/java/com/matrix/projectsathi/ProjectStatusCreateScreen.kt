package com.matrix.projectsathi

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.matrix.projectsathi.presentation.viewmodels.PublishProjectViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectStatusCreateScreen(
    publishProjectViewModel: PublishProjectViewModel,
    navHostController: NavHostController,
    profileImageUrl: Int,
    onPostClick: () -> Unit
) {
    var thoughtText by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var visibilityOption by remember { mutableStateOf("Anyone") }

    var durationText by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("Days") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var amPmStart by remember { mutableStateOf("AM") }
    var amPmEnd by remember { mutableStateOf("PM") }
    var amount by remember { mutableStateOf("") }
    var projectGoal by remember { mutableStateOf("") }
    var technologiesUsed by remember { mutableStateOf("") }
    var startDay by remember { mutableStateOf("Sunday") }
    var endDay by remember { mutableStateOf("Saturday") }

    var textFieldList by remember { mutableStateOf(listOf<String>()) }

    val context = LocalContext.current
    val selectedImages = remember { mutableStateListOf<Uri>() } // Holds the selected images

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        if (uris.isNotEmpty()) {
            selectedImages.clear() // Clear previous selections
            selectedImages.addAll(uris) // Add newly selected images
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
                onClick = {

                    publishProjectViewModel.publishStatus(
                        projectDescription = thoughtText,
                        projectDuration = durationText,
                        durationType = selectedUnit,
                        startTime = "$startTime $amPmStart",
                        endTime = "$endTime $amPmEnd",
                        skillsRequired = textFieldList,
                        projectGoal = projectGoal,
                        technologiesUsed = technologiesUsed,
                        amount = amount,
                        images = selectedImages
                    )
                },
                //enabled = thoughtText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.amber))
            ) {
                Text(text = "Post", color = Color.White)
            }
        }

        OutlinedTextField(
            value = thoughtText,
            onValueChange = { thoughtText = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            placeholder = {
                Text(
                    text = "Project descriptions and requirements ..",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.amber),
                unfocusedBorderColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Actions (Add Image, Add More)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { launcher.launch("image/*") }) {
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

        Spacer(modifier = Modifier.height(16.dp))

        // Project Duration Dropdown
        DurationDropdown(
            durationText = durationText,
            onDurationTextChange = { durationText = it },
            selectedUnit = selectedUnit,
            onUnitSelected = { selectedUnit = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Working Time on Project",
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Available day Section
        Box (
            modifier = Modifier
                .fillMaxWidth()
                //.padding(16.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                TimeSelectionRow(
                    startDay = startDay,
                    onStartDaySelected = { startDay = it },
                    endDay = endDay,
                    onEndDaySelected = { endDay = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Start Time Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = startTime,
                        onValueChange = { // Allow only numeric input
                            if (it.all { char -> char.isDigit() }) {
                                startTime = it
                            }
                        },
                        label = { Text("Start") },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = colorResource(id = R.color.amber)
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AmPmDropdown(
                        selectedOption = amPmStart,
                        onOptionSelected = { amPmStart = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // End Time Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = endTime,
                        onValueChange = { // Allow only numeric input
                            if (it.all { char -> char.isDigit() }) {
                                endTime = it
                            }
                        },
                        label = { Text("End") },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = colorResource(id = R.color.amber)
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AmPmDropdown(
                        selectedOption = amPmEnd,
                        onOptionSelected = { amPmEnd = it }
                    )
                }
            }

        }



        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Add Skills Required",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Loop through the list and display a TextField for each item
        textFieldList.forEachIndexed { index, textValue ->
            TextField(
                value = textValue,
                onValueChange = { newValue ->
                    // Update the list with the new value
                    textFieldList = textFieldList.toMutableList().apply {
                        this[index] = newValue
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = colorResource(id = R.color.amber)
                )
            )
        }


        // Button to add a new TextField
        Button(
            onClick = {
                // Add a new empty text field and trigger recomposition
                textFieldList = textFieldList.toMutableList().apply {
                    add("")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.amber)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Add Skills Required")
        }

        // Amount Field
        TextField(
            value = amount,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    amount = it
                }
            },
            label = { Text("Amount") },
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(id = R.color.amber)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Project Goal Field
        TextField(
            value = projectGoal,
            onValueChange = { projectGoal = it },
            label = { Text("Project Goal") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(id = R.color.amber)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Technologies Used Field
        TextField(
            value = technologiesUsed,
            onValueChange = { technologiesUsed = it },
            label = { Text("Technologies Used") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(id = R.color.amber)
            )
        )

        Spacer(modifier = Modifier.height(100.dp))



    }
}



@Composable
fun DropdownMenuBox(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationDropdown(
    durationText: String,
    onDurationTextChange: (String) -> Unit,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val durationUnits = listOf("Days", "Weeks", "Months")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Duration Input TextField
        TextField(
            value = durationText,
            onValueChange = { // Allow only numeric input
                if (it.all { char -> char.isDigit() }) {
                    onDurationTextChange(it)
                } },
            label = { Text("Project Duration") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(id = R.color.amber)
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Duration Unit Dropdown
        TextField(
            value = selectedUnit,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier
                .width(120.dp)
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            durationUnits.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(text = unit) },
                    onClick = {
                        onUnitSelected(unit)
                        expanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmPmDropdown(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val amPmOptions = listOf("AM", "PM")

    TextField(
        value = selectedOption,
        onValueChange = {},
        //label = { Text("AM/PM") },
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier.clickable { expanded = true }
            )
        },
        modifier = Modifier
            .width(100.dp)
            .clickable { expanded = true },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.amber)
        )
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        amPmOptions.forEach { option ->
            DropdownMenuItem(
                text = { Text(text = option) },
                onClick = {
                    onOptionSelected(option)
                    expanded = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectionRow(
    startDay: String,
    onStartDaySelected: (String) -> Unit,
    endDay: String,
    onEndDaySelected: (String) -> Unit
) {
    var startDayExpanded by remember { mutableStateOf(false) }
    var endDayExpanded by remember { mutableStateOf(false) }
    val daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Start Day Dropdown TextField
        TextField(
            value = startDay,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.clickable { startDayExpanded = true }
                )
            },
            modifier = Modifier
                .weight(1f)
                .clickable { startDayExpanded = true }
        )

        DropdownMenu(
            expanded = startDayExpanded,
            onDismissRequest = { startDayExpanded = false }
        ) {
            daysOfWeek.forEach { day ->
                DropdownMenuItem(
                    text = { Text(text = day) },
                    onClick = {
                        onStartDaySelected(day)
                        startDayExpanded = false
                    }
                )
            }
        }

        Text(text = "to", modifier = Modifier.padding(top = 20.dp), fontSize = 16.sp)

        // End Day Dropdown TextField
        TextField(
            value = endDay,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.clickable { endDayExpanded = true }
                )
            },
            modifier = Modifier
                .weight(1f)
                .clickable { endDayExpanded = true }
        )

        DropdownMenu(
            expanded = endDayExpanded,
            onDismissRequest = { endDayExpanded = false }
        ) {
            daysOfWeek.forEach { day ->
                DropdownMenuItem(
                    text = { Text(text = day) },
                    onClick = {
                        onEndDaySelected(day)
                        endDayExpanded = false
                    }
                )
            }
        }
    }
}

