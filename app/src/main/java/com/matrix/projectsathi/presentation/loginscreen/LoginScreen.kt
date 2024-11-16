package com.matrix.projectsathi.presentation.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.viewmodels.AuthViewModel
import com.matrix.projectsathi.presentation.viewmodels.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    val loginState by authViewModel.loginState.observeAsState(LoginState.Loading)

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
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
    ) { it ->

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Image(painter = painterResource(id = R.drawable.login_sticker), contentDescription =null )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Login to ProjectSathi",
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.darkCharcoal),
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your details below to",
                color = colorResource(id = R.color.darkCharcoal),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "login to your ProjectSathi",
                color = colorResource(id = R.color.darkCharcoal),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "account.",
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
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Email Address", color = Color.Gray)
                        },
                        textStyle = TextStyle(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Password:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    //password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Password", color = Color.Gray)
                        },
                        textStyle = TextStyle(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //login button
                    Button(
                        onClick = {
                            if(email.isNotEmpty() || password.isNotEmpty()){
                                authViewModel.login(email, password)
                            }
                        },
                        modifier = Modifier
                            .height(38.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.darkCharcoal))
                    ) {
                        Text(text = "Login")
                    }

                    Spacer(modifier = Modifier.height(16.dp))



                }
            }


        }
    }

    // Show loading or error states
    when (loginState) {
        is LoginState.Loading -> {
            CircularProgressIndicator()
        }

        is LoginState.Success -> {
            LaunchedEffect(Unit) {
                onLoginSuccess() // Navigate to next screen after successful login
            }
        }

        is LoginState.Error -> {
            Text(text = (loginState as LoginState.Error).message, color = Color.Red)
        }

        LoginState.Default -> {

        }
    }
}