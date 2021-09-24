package com.beatrice.greetingsapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beatrice.greetingsapp.ui.theme.GreetingsAppTheme
import com.beatrice.greetingsapp.ui.view.composables.Greeting
import com.beatrice.greetingsapp.ui.view.composables.Profile
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MyViewModel = getViewModel()
            val name = viewModel.name.value
            val greetings = viewModel.greetings.value
            // Survives configuration changes using rememberValue()
            val navController = rememberNavController() // Should this be here 
            GreetingsAppTheme {
                // or here
                Scaffold(
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Filled.Face,
                                        contentDescription = ""
                                    )

                                }
                            },
                            title = {
                                Text(text = "Greetings")
                            },

                        )
                    },
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "greetings",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("greetings") {
                                Greeting(
                                    greetings = greetings,
                                    name = name,
                                    onTextChanged = viewModel::onTextChanged,
                                    onButtonClick = { navController.navigate("profile"){
                                        popUpTo("greetings") {inclusive = true}
                                    } }
                                )
                            }
                            composable("profile") {
                                Profile(name = name)
                            }
                        }

                    }
                )

            }
        }
    }
}

