package com.example.uipractice

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.uipractice.ui.theme.UIPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIPracticeTheme {

                NavigationBarScreen()
            }
        }
    }
}

enum class Destination(
    val route: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Filled.Home),
    Challenges("Challenges", Icons.Filled.DateRange),
    Leaderboard("Leaderboard", Icons.Filled.Menu),
    Profile("Profile", Icons.Filled.Person)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun NavigationBarScreen() {
    val navController = rememberNavController()
    val startDestination = Destination.Home
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }
    Scaffold(
        modifier = Modifier.padding(),


        topBar = {
            TopAppBar(
                navigationIcon = {   Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier.padding(start = 6.dp, end = 7.dp).size(30.dp)
                ) },
                title = {
                    Text("CodeSprint Dashboard", fontSize = 20.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                    titleContentColor = androidx.compose.ui.graphics.Color(Color.WHITE),
                    actionIconContentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "")
                    }

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "")
                    }
                }

            )
        },
        bottomBar = {

            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets, containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F), contentColor = androidx.compose.ui.graphics.Color(Color.WHITE), ) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = destination
                        },
                        icon =  {
                            Icon(destination.icon, contentDescription = destination.route, tint = androidx.compose.ui.graphics.Color(
                                Color.LTGRAY))
                        },
                        label = { Text(destination.route, color = androidx.compose.ui.graphics.Color(Color.LTGRAY)) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT)
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(navController, startDestination.route, modifier = Modifier.padding(contentPadding)) {
            composable("Home") {
                Home()
            }
            composable("Challenges") {

            }
            composable("Leaderboard") {

            }
            composable("Profile") {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Home() {
    var input by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column() {
        LazyColumn(modifier = Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color(Color.BLACK))) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize().semantics { isTraversalGroup = true }
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SearchBar(
                            modifier = Modifier
                                .semantics { traversalIndex = 0f },
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            inputField = {
                                SearchBarDefaults.InputField(
                                    query = input,
                                    onQueryChange = { input = it },
                                    onSearch = { expanded = false },
                                    expanded = expanded,
                                    onExpandedChange = { expanded = it },
                                    placeholder = {
                                        Row() {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = null
                                            )
                                            Text(
                                                "Find Challenges...",
                                                modifier = Modifier.padding(start = 9.dp)
                                            )
                                        }
                                    }
                                )
                            }
                        ) {
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth().height(56.dp)
                                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
                        ) {
                            Text("Start New Challenge")
                        }
                    }
                }

            }
            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
                    Text(
                        "Your Progress",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(Color.WHITE)
                    )

                    Text("16ep",fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY))


                }

                Card(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp).height(130.dp).fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                    contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                )) {

                    Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 20.dp, start = 19.dp, end = 19.dp, bottom = 9.dp).fillMaxWidth()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            ) {
                            CircularProgressIndicator(
                                progress = { 0.7f },
                                modifier = Modifier.size(94.dp),
                                strokeWidth = 8.dp,
                                color = androidx.compose.ui.graphics.Color(0xFF6200EE)
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(0.dp)) {
                                Text("85%", fontSize = 19.sp, lineHeight = 15.sp)
                                Text(
                                    "Slf50 Solved",
                                    color = androidx.compose.ui.graphics.Color(Color.LTGRAY),
                                    fontSize = 10.sp,
                                    lineHeight = 15.sp
                                )
                                Text(
                                    "Lv6",
                                    color = androidx.compose.ui.graphics.Color(Color.LTGRAY),
                                    fontSize = 10.sp,
                                    lineHeight = 15.sp
                                )
                            }




                        }

                        Column() {
                            Row() {
                                Text("Points:", modifier = Modifier.padding(end = 4.dp), color = androidx.compose.ui.graphics.Color(Color.LTGRAY))
                                Text("12,450")
                            }
                            Row() {
                                Text("Rank:", modifier = Modifier.padding(end = 4.dp), color = androidx.compose.ui.graphics.Color(Color.LTGRAY))
                                Text("432")
                            }
                            Row() {
                                Text("Streak:", modifier = Modifier.padding(end = 4.dp), color = androidx.compose.ui.graphics.Color(Color.LTGRAY))
                                Text("14 Days")

                                }
                        }
                    }


                }

            }

            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
                    Text(
                        "Your Featured Challenges",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(Color.WHITE)
                    )

                    Text("16sp",fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY))


                }

                LazyRow() {

                    item {
                        Card(
                            modifier = Modifier.padding(top = 10.dp, start = 9.dp)
                                .height(190.dp).width(175.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                                contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                            )
                        ) {

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                                    Icon(
                                        Icons.Filled.Settings,
                                        contentDescription = "Gear",
                                        modifier = Modifier.size(50.dp).padding(top = 12.dp),
                                        tint = androidx.compose.ui.graphics.Color(
                                            0xFF6200EE
                                        )
                                    )
                                    Text(
                                        "Algorithmic Progress",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY),
                                        modifier = Modifier.padding(7.dp)
                                    )
                                }
                                Card(modifier = Modifier.width(49.dp).height(37.dp).padding(start = 8.dp), colors = CardDefaults.cardColors(
                                    containerColor = androidx.compose.ui.graphics.Color(Color.RED),

                                )) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            "Hard",
                                            color = androidx.compose.ui.graphics.Color(Color.WHITE),
                                            fontSize = 13.sp, modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                                        )
                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Progress: 80%", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp))
                                }
                                LinearProgressIndicator(
                                    progress = { 0.55f },
                                    modifier = Modifier.fillMaxWidth().padding(start = 9.dp, end = 9.dp, top = 3.dp),
                                    color = androidx.compose.ui.graphics.Color(0xFF6200EE)
                                )

                                Text("250pts", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp, top = 4.dp))
                            }
                    }




                    }
                    item {
                        Card(
                            modifier = Modifier.padding(top = 10.dp, start = 9.dp)
                                .height(190.dp).width(175.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                                contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                            )
                        ) {

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                                    Icon(
                                        Icons.Filled.PhoneAndroid,
                                        contentDescription = "Gear",
                                        modifier = Modifier.size(50.dp).padding(top = 12.dp),
                                        tint = androidx.compose.ui.graphics.Color(
                                            Color.BLUE
                                        )
                                    )
                                    Text(
                                        "UI Masterclass",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY),
                                        modifier = Modifier.padding(7.dp)
                                    )
                                }
                                Card(modifier = Modifier.width(70.dp).height(37.dp).padding(start = 8.dp, top = 5.dp, bottom = 5.dp), colors = CardDefaults.cardColors(
                                    containerColor = androidx.compose.ui.graphics.Color(Color.BLUE),

                                    )) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            "Medium",
                                            color = androidx.compose.ui.graphics.Color(Color.WHITE),
                                            fontSize = 13.sp, modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                                        )
                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Progress: 80%", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp))
                                }
                                LinearProgressIndicator(
                                    progress = { 0.55f },
                                    modifier = Modifier.fillMaxWidth().padding(start = 9.dp, end = 9.dp, top = 3.dp, bottom = 5.dp),
                                    color = androidx.compose.ui.graphics.Color(0xFF6200EE)
                                )

                                Text("180pts", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp, top = 4.dp))
                            }
                        }




                    }
                    item {
                        Card(
                            modifier = Modifier.padding(top = 10.dp, start = 9.dp)
                                .height(190.dp).width(175.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                                contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                            )
                        ) {

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                                    Icon(
                                        Icons.Filled.PhoneAndroid,
                                        contentDescription = "Gear",
                                        modifier = Modifier.size(50.dp).padding(top = 12.dp),
                                        tint = androidx.compose.ui.graphics.Color(
                                            Color.BLUE
                                        )
                                    )
                                    Text(
                                        "UI Masterclass",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY),
                                        modifier = Modifier.padding(7.dp)
                                    )
                                }
                                Card(modifier = Modifier.width(70.dp).height(37.dp).padding(start = 8.dp, top = 5.dp, bottom = 5.dp), colors = CardDefaults.cardColors(
                                    containerColor = androidx.compose.ui.graphics.Color(Color.BLUE),

                                    )) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            "Medium",
                                            color = androidx.compose.ui.graphics.Color(Color.WHITE),
                                            fontSize = 13.sp, modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                                        )
                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Progress: 80%", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp))
                                }
                                LinearProgressIndicator(
                                    progress = { 0.55f },
                                    modifier = Modifier.fillMaxWidth().padding(start = 9.dp, end = 9.dp, top = 3.dp, bottom = 5.dp),
                                    color = androidx.compose.ui.graphics.Color(0xFF6200EE)
                                )

                                Text("180pts", fontSize = (15.sp), fontWeight = FontWeight.Medium, color = androidx.compose.ui.graphics.Color(Color.LTGRAY),modifier = Modifier.padding(start = 10.dp, top = 4.dp))
                            }
                        }




                    }




                }
            }

            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
                    Text(
                        "Available Categories",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(Color.WHITE)
                    )

                    Text("16sp",fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY))


                }
                LazyRow(modifier = Modifier.padding(start = 7.dp)) {
                    item() {
                        Button(onClick = {}, modifier = Modifier.padding(start = 8.dp), border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Gray), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFFD1C4E9),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("All Challenges")
                        }
                    }
                    item {
                        Button(onClick = {}, modifier = Modifier.padding(start = 8.dp), border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Gray), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("Kotlin")
                        }
                    }

                    item {
                        Button(onClick = {}, modifier = Modifier.padding(start = 8.dp), border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Gray), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("Data Structures")
                        }
                    }

                    item {
                        Button(onClick = {},modifier = Modifier.padding(start = 8.dp), border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Gray), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(Color.TRANSPARENT),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("UI/UX")
                        }
                    }



                }
            }

            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
                    Text(
                        "My Challenges",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(Color.WHITE)
                    )

                    Text("16sp",fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color(Color.LTGRAY))


                }

            }


            item {
                Card(modifier = Modifier.height(120.dp).fillMaxWidth().padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 15.dp), colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                    contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                )) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                        Image(
                            painter = painterResource(id = R.drawable.star_off),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Column( modifier = Modifier.padding(start = 10.dp, top = 6.dp)) {
                            Text("Memory Management", lineHeight = 10.sp, fontSize = 17.sp)
                            Text("Kotlin: 10 days off",lineHeight = 15.sp,fontSize = 13.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Pending Review", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text(".", fontSize = 15.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text("Status Bar", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            }
                        }

                        Button(onClick = {}, modifier = Modifier.padding(start = 10.dp, end = 10.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFF90CAF9),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("Resume")
                        }
                    }
                }
            }
            item {
                Card(modifier = Modifier.height(120.dp).fillMaxWidth().padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 15.dp), colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                    contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                )) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                        Image(
                            painter = painterResource(id = R.drawable.star_off),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Column( modifier = Modifier.padding(start = 10.dp, top = 6.dp)) {
                            Text("Memory Management", lineHeight = 10.sp, fontSize = 17.sp)
                            Text("Kotlin: 10 days off",lineHeight = 15.sp,fontSize = 13.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Pending Review", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text(".", fontSize = 15.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text("Status Bar", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            }
                        }

                        Button(onClick = {}, modifier = Modifier.padding(start = 10.dp, end = 10.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFF90CAF9),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("Resume")
                        }
                    }
                }
            }
            item {
                Card(modifier = Modifier.height(120.dp).fillMaxWidth().padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 15.dp), colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF2C2A2F),
                    contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                )) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                        Image(
                            painter = painterResource(id = R.drawable.star_off),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Column( modifier = Modifier.padding(start = 10.dp, top = 6.dp)) {
                            Text("Memory Management", lineHeight = 10.sp, fontSize = 17.sp)
                            Text("Kotlin: 10 days off",lineHeight = 15.sp,fontSize = 13.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Pending Review", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text(".", fontSize = 15.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.YELLOW))

                                Text("Status Bar", fontSize = 13.sp, lineHeight = 10.sp, color = androidx.compose.ui.graphics.Color(Color.WHITE))
                            }
                        }

                        Button(onClick = {}, modifier = Modifier.padding(start = 10.dp, end = 10.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFF90CAF9),
                            contentColor = androidx.compose.ui.graphics.Color(Color.WHITE)
                        )) {
                            Text("Resume")
                        }
                    }
                }
            }


        }
    }
}

