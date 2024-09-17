package com.example.greetings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetings.ui.theme.GreetingsTheme
import java.util.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingApp(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var greeting by remember { mutableStateOf("") }

    fun getGreetingMessage(name: String): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val morningGreetings = listOf(
            "Good morning, $name! Rise and shine!",
            "Morning, $name! Hope you have a great day ahead!",
            "Hello $name, a beautiful morning to start your day!"
        )

        val afternoonGreetings = listOf(
            "Good afternoon, $name! Keep up the great work!",
            "Hi $name, wishing you a productive afternoon!",
            "Hello $name, hope your afternoon is going well!"
        )

        val eveningGreetings = listOf(
            "Good evening, $name! How was your day?",
            "Evening, $name! Time to relax and unwind.",
            "Hi $name, enjoy your evening!"
        )

        val nightGreetings = listOf(
            "Hello $name! Burning the midnight oil?",
            "Hi $name! Late night, isn't it?",
            "Hello $name! Hope you had a wonderful day!"
        )

        val greetingsList = when (hour) {
            in 5..11 -> morningGreetings
            in 12..17 -> afternoonGreetings
            in 18..22 -> eveningGreetings
            else -> nightGreetings
        }

        return greetingsList[Random.nextInt(greetingsList.size)]
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Please enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
            if (name.text.isNotBlank()) {
                greeting = getGreetingMessage(name.text)
            }
        },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp)
            ) {
            Text(
                text = "Greet Me",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (greeting.isNotEmpty()) {
            Text(
                text = greeting,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingAppPreview() {
    GreetingsTheme {
        GreetingApp()
    }
}
