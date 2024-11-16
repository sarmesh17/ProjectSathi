package com.matrix.projectsathi.presentation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.matrix.projectsathi.R

@Composable
fun BottomNavigation( navHostController: NavHostController,onClick:(index:Int) -> Unit, selectedItem: Int) {

    val items = listOf(
        NavigationItem("Projects", R.drawable.project_icon_filled, R.drawable.project_icon_outline),
        NavigationItem("Requests", R.drawable.request_filled, R.drawable.request_outlined),
        NavigationItem("Save", R.drawable.save_filled, R.drawable.save_outline),
        NavigationItem("Notification", R.drawable.notification_filled,
            R.drawable.notification_outline
        ),
        NavigationItem("Profile", R.drawable.profile_filled, R.drawable.profile_outline)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
    ) {
        items.forEachIndexed{ index, item->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onClick(index) },
                label = {
                    if ( index == selectedItem){
                        Text(
                            text = item.name,
                            maxLines = 1,
                            fontSize = 11.sp,
                            color = colorResource(id = R.color.amber)
                        )
                    }else{
                        Text(
                            text = item.name,
                            maxLines = 1,
                            fontSize = 11.sp
                        )
                    }

                },
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        //modifier = Modifier.width(50.dp)
                    ){
                        // Place the indicator above the icon
                        if (selectedItem == index) {
                            Box(
                                modifier = Modifier
                                    .size(width = 50.dp, height = 3.dp) // Indicator size
                                    .background(color = colorResource(id = R.color.amber)) // Customize indicator appearance
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 8.dp))
                        Icon(
                            painter = /*painterResource(id = item.icon),*/
                            if (index == selectedItem) {
                                painterResource(id = item.selectedIcon)
                            }else painterResource(id = item.unselectedIcon),
                            contentDescription = item.name,
                            tint = if ( index == selectedItem){
                                colorResource(id = R.color.amber)
                            }else{
                                Color.Black
                            },
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}

data class NavigationItem(
    val name: String,
    @DrawableRes val selectedIcon: Int ,
    @DrawableRes val unselectedIcon: Int

)

@Preview(showSystemUi = true)
@Composable
fun ButtonNavPreview (){
    BottomNavigation( navHostController = rememberNavController(),onClick = {}, selectedItem = 0)
}