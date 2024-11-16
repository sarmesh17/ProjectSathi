package com.matrix.projectsathi.presentation.signup_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matrix.projectsathi.R
import com.matrix.projectsathi.presentation.viewmodels.AuthViewModel
import com.matrix.projectsathi.presentation.viewmodels.SignUpState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel:AuthViewModel,
    onSignUpSuccess: () -> Unit

) {
    val signUpState by viewModel.signUpState.observeAsState()

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var firstNameMentor by remember {
        mutableStateOf("")
    }
    var lastNameMentor by remember {
        mutableStateOf("")
    }
    var isChecked by remember { mutableStateOf(false) }
    var isCheckedTerms by remember { mutableStateOf(false) }
    var isCheckedPolicy by remember { mutableStateOf(false) }
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
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Join ProjectSathi",
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.darkCharcoal),
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Join our global mentoring\n community today, either as\n an individual to find your\n mentor and mentor others, or\n by requesting a demo of our\n solution to help your\n organixation's mentoring\n program.",
                color = colorResource(id = R.color.darkCharcoal),
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Join us to Find a Mentor or Become a Mentor",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            append("Join our open and free mentoring network in a few short steps, to ")
                            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                            append("find a mentor, become a mentor or do both.")
                            pop() // Revert back to normal style
                        }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    HorizontalDivider(color = Color.Gray)
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "First Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "First Name", color = Color.Gray)
                                },
                                textStyle = TextStyle(fontSize = 20.sp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Last Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = lastName,
                                onValueChange = { lastName = it },
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "Last Name", color = Color.Gray)
                                },
                                textStyle = TextStyle(fontSize = 20.sp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))

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

                    //im not robot verify
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(color = colorResource(id = R.color.white_sand))
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { isChecked = !isChecked }
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked = it }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "I'm not a robot"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(id = R.drawable.recaptcha_img),
                                contentDescription = null,
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // checkbox terms of use
                            Row(verticalAlignment = Alignment.CenterVertically) {


                                Checkbox(
                                    checked = isCheckedTerms,
                                    onCheckedChange = { isCheckedTerms = it }
                                )
                                Text(
                                    text = "I have read and agree to Terms of Use",
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {


                                //checkbox privacy policy
                                Checkbox(
                                    checked = isCheckedPolicy,
                                    onCheckedChange = { isCheckedPolicy = it }
                                )
                                Text(
                                    text = "I have read and agree to Privacy Policy",
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))


                    Button(
                        onClick = {
                            viewModel.signUp(firstName, lastName, email, password)
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.darkCharcoal))
                    ) {
                        Text(text = "Join Now - FREE")
                    }


                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    when (signUpState) {
        is SignUpState.Loading -> CircularProgressIndicator()
        is SignUpState.Success -> {
            onSignUpSuccess()
        }
        is SignUpState.Error -> {
            Text(
                text = (signUpState as SignUpState.Error).message,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        else -> {}
    }
}
