package com.matrix.projectsathi.presentation.dashboard.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.dashboard.searchbar.SearchBar

@Composable
@Preview(showSystemUi = true)
fun AppBar() {

    var searchQuery by remember {
        mutableStateOf("")
    }

    Card(colors = CardDefaults.cardColors(Color.White)) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop,

                )

            Spacer(modifier = Modifier.width(12.dp))

            SearchBar(
                query = searchQuery,
                onQueryChanged = {
                    searchQuery = it
                }
            ) {

            }

            Spacer(modifier = Modifier.width(12.dp))


            Icon(
                painter = painterResource(id = R.drawable.addition),
                contentDescription = null,
                tint = colorResource(
                    id = R.color.black
                ), modifier = Modifier.size(23.dp)
            )


            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.chat_icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colorResource(id = R.color.black)
            )

        }
    }

}