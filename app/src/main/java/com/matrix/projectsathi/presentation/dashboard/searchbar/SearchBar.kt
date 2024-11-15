package com.matrix.projectsathi.presentation.dashboard.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(250.dp)
            .padding(4.dp),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {


        Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text field

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color(0xFFeef3f7)), contentAlignment = Alignment.CenterStart
        ) {

            Row (verticalAlignment = Alignment.CenterVertically){

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black// Color of the search icon
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Search..")

            }




        }


    }
}




