package com.matrix.projectsathi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun LoginScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Project",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.amber),
                        fontWeight = FontWeight.ExtraBold

                    )
                    Text(
                        text = "Sathi",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.darkCharcoal),
                        fontWeight = FontWeight.ExtraBold

                    )
                }
            })
        }
    ){it ->

    Column (
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Login to ProjectSathi",
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.darkCharcoal),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter your details below to\n login to your ProjectSathi\n account.",
            color = colorResource(id = R.color.darkCharcoal),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        // login card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Email Address:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                //email
                OutlinedTextField(
                    value = email,
                    onValueChange ={ email = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Email Address", color = Color.Gray)
                    },
                    textStyle = TextStyle( fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Password:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                //password
                OutlinedTextField(
                    value = password,
                    onValueChange ={ password = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Password", color = Color.Gray)
                    },
                    textStyle = TextStyle( fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                //login button
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(38.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.darkCharcoal))
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "- OR -", fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(32.dp))

                //login with sso
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(38.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.amber))
                ) {
                    Text(text = "Login with SSO (Single Sign-On)")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Image(painter = painterResource(id = R.drawable.bg_design), contentDescription = null, modifier = Modifier.size(400.dp))

    }
    }
}