package com.matrix.projectsathi.presentation.dashboard.feed_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.navigation.Routes

@Composable
fun FeedScreen(
    userName: String,
    userRole: String = "Android Developer",
    statusTime: String = "1W",
    images: List<String>?,
    statusText: String?,
    skills: List<String>?,
    totalAmount: String,navHostController: NavHostController
    ) {

    var isCommentSectionVisible by remember { mutableStateOf(false) }
    var comments by remember { mutableStateOf(listOf<String>()) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp)
            .background(color = Color.White)
    ) {
        // User Info Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp)
        ) {

//            AsyncImage(
//                model = images,
//                contentDescription = null,
//                modifier = Modifier.size(140.dp, 150.dp).padding(top = 12.dp),)
            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape), contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = userName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = userRole, color = colorResource(id = R.color.grey))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = statusTime, fontSize = 12.sp)
                    Text(text = " '")
                    Spacer(modifier = Modifier.width(1.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.planet_icon),
                        contentDescription = null,
                        tint = colorResource(id = R.color.grey),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        when {
            // Case 1: Both Text and Single Image
            statusText != null && images != null && images.size == 1 -> {
                StatusTextWithSeeMore(statusText)
                AsyncImage(
                    model = images.first(), // Assuming images.first() gives you the URL as a String
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    contentScale = ContentScale.Crop
                )

            }
            // Case 2: Both Text and Multiple Images
            statusText != null && images != null && images.size > 1 -> {
                StatusTextWithSeeMore(statusText)
                DisplayImageGrid(images)
            }
            // Case 3: Only Text Status
            statusText != null -> {
                StatusTextWithSeeMore(statusText)
            }
            // Case 4: Only One Image (Full Width)
            images != null && images.size == 1 -> {
                AsyncImage(
                    model = images.first(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(230.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            // Case 5: Only Multiple Images
            images != null && images.size > 1 -> {
                DisplayImageGrid(images)
            }
            // Case 6: No Content
            else -> {
                Text(
                    text = "No status to display.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(id = R.color.grey),
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                )
            }
        }

        // Project Skills and Amount Section
        if (skills != null) {
            SkillsAndAmountSection(
                skills = skills, totalAmount = totalAmount
            )
        }

        // Buttons Row
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ToggleButtonWithIcon(
                filledIcon = painterResource(id = R.drawable.filled_thumbs_up),
                unfilledIcon = painterResource(id = R.drawable.outlined_thumbs_icon),
                text = "Like",
                modifier = Modifier.weight(1f)
            )

            ButtonWithIcon(
                icon = painterResource(id = R.drawable.connect),
                text = "Connect",
                modifier = Modifier.weight(1f).clickable {
                    navHostController.navigate(Routes.ProjectDetailScreen)
                }
            )

            ToggleButtonWithIcon(
                filledIcon = painterResource(id = R.drawable.save_filled),
                unfilledIcon = painterResource(id = R.drawable.save_unfilled),
                text = "Save",
                modifier = Modifier.weight(1f)
            )
            ButtonWithIcon(
                icon = painterResource(id = R.drawable.share),
                text = "Share",
                modifier = Modifier.weight(1f)
            )

        }


    }


}

@Composable
fun ButtonWithIcon(icon: Painter, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colorResource(id = R.color.grey)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 12.sp, color = colorResource(id = R.color.grey))
    }
}

@Composable
fun ToggleButtonWithIcon(
    filledIcon: Painter,
    unfilledIcon: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable { isSelected = !isSelected },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = if (isSelected) filledIcon else unfilledIcon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colorResource(id = R.color.grey)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 12.sp, color = colorResource(id = R.color.grey))
    }
}

@Composable
fun StatusTextWithSeeMore(text: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val maxLines = if (isExpanded) Int.MAX_VALUE else 3

    Column {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )
        if (!isExpanded && text.length > 100) {  // Show "See More" if text is lengthy
            Text(
                text = "See More",
                fontSize = 14.sp,
                color = colorResource(id = R.color.grey),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { isExpanded = true }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun DisplayImageGrid(images: List<String>?) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Safely handle null case for images
        images?.let {
            items(it) { imageRes ->
                AsyncImage(
                    model = imageRes,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun SkillsAndAmountSection(skills: List<String>, totalAmount: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.orange),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Skills Required",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black)
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )

        // Skills List
        skills.forEach { skill ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = colorResource(id = R.color.orange),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = skill,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total Amount
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Amount:",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "$${totalAmount}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
            )
        }
    }
}


