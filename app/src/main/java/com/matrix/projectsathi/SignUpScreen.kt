package com.matrix.projectsathi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
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
                        text = "Join Free to Find a Mentor or Become a Mentor",
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

                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Column {
                            Text(text = "First Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = firstName,
                                onValueChange ={ firstName = it},
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "First Name", color = Color.Gray)
                                },
                                textStyle = TextStyle( fontSize = 20.sp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Last Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = lastName,
                                onValueChange ={ lastName = it},
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "Last Name", color = Color.Gray)
                                },
                                textStyle = TextStyle( fontSize = 20.sp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))

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

                    //im not robot verify
                    Box (
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
                                .padding(16.dp)
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
                            Image(painter = painterResource(id = R.drawable.recaptcha_img), contentDescription = null, modifier = Modifier.size(70.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // checkbox terms of use
                            Checkbox(
                                checked = isCheckedTerms,
                                onCheckedChange = { isCheckedTerms = it }
                            )
                            Text(text = "I have read and agree to ProjectSathi's Terms of Use")
                            Spacer(modifier = Modifier.height(16.dp))

                            //checkbox privacy policy
                            Checkbox(
                                checked = isCheckedPolicy,
                                onCheckedChange = { isCheckedPolicy = it }
                            )
                            Text(text = "I have read and agree to ProjectSathi's Privacy Policy")

                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))


                    Button(
                        onClick = { /*TODO*/ },
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
            Text(
                text = "- OR -",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
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
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Run Your Own\nMentoring Programs",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Image(painter = painterResource(id = R.drawable.mentor_img), contentDescription = null, modifier = Modifier.size(100.dp))
                    }

                    Text(
                        text = "If you are looking to run your own internal, closed and private mentoring programs for your employees, students or membership communities, we can help. Request a demo using the form below.",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    HorizontalDivider(color = Color.Gray)
                    Spacer(modifier = Modifier.height(25.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Column {
                            Text(text = "First Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = firstNameMentor,
                                onValueChange ={ firstNameMentor = it},
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "First Name", color = Color.Gray)
                                },
                                textStyle = TextStyle( fontSize = 20.sp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Last Name:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = lastNameMentor,
                                onValueChange ={ lastNameMentor = it},
                                modifier = Modifier.width(165.dp),
                                placeholder = {
                                    Text(text = "Last Name", color = Color.Gray)
                                },
                                textStyle = TextStyle( fontSize = 20.sp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(text = "Work Email Address:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    //email
                    OutlinedTextField(
                        value = email,
                        onValueChange ={ email = it},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Work Email Address", color = Color.Gray)
                        },
                        textStyle = TextStyle( fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Once you submit this form a member of our customer success team will be in touch an arrange a time for an introductory call and demo of ProjectSathi's mentoring software solution.",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    // request button
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.amber))
                    ) {
                        Text(text = "Request A Demo")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            Spacer(modifier = Modifier.height(250.dp))

        }
    }
}