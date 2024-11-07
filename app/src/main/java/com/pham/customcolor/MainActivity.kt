/**
 * MainActivity
 *
 * @author James Pham
 */

package com.pham.customcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.unit.dp
import com.pham.customcolor.ui.theme.CustomColorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomColorTheme {
                CustomColorApp()
            }
        }
    }
}

@Composable
fun CustomColorApp() {
    var selectedElement by remember { mutableStateOf("None") }
    var selectedColor by remember { mutableStateOf(ComposeColor.Red) }
    var red by remember { mutableStateOf(255f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }

    val elementColors = remember {
        mutableStateMapOf(
            "Rectangle" to ComposeColor.Red,
            "Circle" to ComposeColor.Green
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display selected element's name
        Text(text = "Selected Element: $selectedElement", style = MaterialTheme.typography.bodyLarge)

        // Shapes as Boxes with Modifier.clickable for interaction
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Rectangle with Rounded Corners
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(elementColors["Rectangle"] ?: ComposeColor.Red)
                    .clickable {
                        selectedElement = "Rectangle"
                        selectedColor = elementColors["Rectangle"] ?: ComposeColor.Red
                        red = (selectedColor.red * 255).toFloat()
                        green = (selectedColor.green * 255).toFloat()
                        blue = (selectedColor.blue * 255).toFloat()
                    }
            )

            // Circle Shape using Box and CircleShape
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(elementColors["Circle"] ?: ComposeColor.Green)
                    .clickable {
                        selectedElement = "Circle"
                        selectedColor = elementColors["Circle"] ?: ComposeColor.Green
                        red = (selectedColor.red * 255).toFloat()
                        green = (selectedColor.green * 255).toFloat()
                        blue = (selectedColor.blue * 255).toFloat()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // RGB Sliders for color adjustments
        Text(text = "Red")
        Slider(value = red, onValueChange = {
            red = it
            updateSelectedElementColor(red, green, blue, elementColors, selectedElement)
        }, valueRange = 0f..255f)

        Text(text = "Green")
        Slider(value = green, onValueChange = {
            green = it
            updateSelectedElementColor(red, green, blue, elementColors, selectedElement)
        }, valueRange = 0f..255f)

        Text(text = "Blue")
        Slider(value = blue, onValueChange = {
            blue = it
            updateSelectedElementColor(red, green, blue, elementColors, selectedElement)
        }, valueRange = 0f..255f)
    }
}

// Helper function to update the color of the selected element
fun updateSelectedElementColor(
    red: Float,
    green: Float,
    blue: Float,
    elementColors: MutableMap<String, ComposeColor>,
    selectedElement: String
) {
    val newColor = ComposeColor(red / 255f, green / 255f, blue / 255f)
    if (selectedElement in elementColors) {
        elementColors[selectedElement] = newColor
    }
}
