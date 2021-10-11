package com.beatrice.greetingsapp.greetingsApp.ui.view.composables

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beatrice.greetingsapp.greetingsApp.data.Choices

@Composable
fun OpenEndedAnsContainer(answer: String, onAnswered: (String) -> Unit) {
    TextField(
        value = answer,
        onValueChange = onAnswered,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.DarkGray
        ),
    )
}


@Composable
fun MultipleChoicesContainer(onOptionSelected: (String) -> Unit, answer: String) {
    Column {
        Choices.values().forEach { choice ->
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 4.dp)
                    .selectable(
                        selected = (answer == choice.name),
                        onClick = {
                            onOptionSelected(choice.name)
                        },
                    ),
                horizontalArrangement = Arrangement.Start
            ) {

                Column {
                    Spacer(modifier = Modifier.height(3.dp))
                    Surface(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.3.dp, color = Color.DarkGray)
                    ) {
                        val context = LocalContext.current
                        RadioButton(
                            selected = (answer == choice.name),
                            onClick = {
                                onOptionSelected(choice.name)
                                Toast.makeText(
                                    context,
                                    "Clicked $answer",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            },

                            )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = choice.name,
                    fontFamily = FontFamily.Monospace,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            }
        }

    }

}