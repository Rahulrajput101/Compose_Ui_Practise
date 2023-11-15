package com.ondevop.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ondevop.composeui.ui.MainScreen
import com.ondevop.composeui.ui.theme.ComposeUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiTheme {
                    MainScreen()
            }
        }
    }
}


@Composable
fun BasicShapes() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
    ) {

        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Green,
            topLeft = Offset(100f,100f),
            size = Size(100f,100f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf( Color.Magenta, Color.Cyan),
                center = center,
                radius = 100f
            ),
            radius = 100f,

        )

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = Offset(100f,500f),
            size = Size(200f,200f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(300f,700f),
            end  = Offset(700f,700f),
            strokeWidth = 5.dp.toPx()
        )
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}