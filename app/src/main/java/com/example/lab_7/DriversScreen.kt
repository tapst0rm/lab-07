package com.example.lab_7
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DriversScreen(navController: NavController) {
    var location by remember {
        mutableStateOf("")
    }

    var drivers = remember {
        mutableStateListOf<String>()
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // back button
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.testTag("backButton") // for testing
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // title text
        Text(
            text = "FormulaZero Drivers",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // text field and button to add race locations
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = location,
                onValueChange = {
                    location = it // update location to new value
                },
                label = {
                    Text("Driver Name")
                },
                modifier = Modifier.testTag("locationInput") // for testing
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (location.isNotBlank()) {
                        drivers.add(location)
                        location = ""
                    }
                },
                modifier = Modifier.testTag("addDriverButton") // for testing
            ) {
                Text("Add")
            }
        }
        Button(
            onClick = {
                drivers.clear()
            },
            modifier = Modifier.testTag("clearDriverButton") // for testing
        ) {
            Text("Clear All")
        }


        // list to display races
        LazyColumn {
            items(drivers) { driver ->
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = driver,
                    fontSize = 24.sp
                )
            }
        }
    }
}