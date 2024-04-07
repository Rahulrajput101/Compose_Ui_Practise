package com.ondevop.composeui.ui.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathOperations(){

    Canvas(modifier = Modifier.fillMaxSize()) {

        val squareWithoutOp = Path().apply{
            addRect(Rect(Offset(200f,200f), Size(200f,200f)))
        }

        val circle = Path().apply {
            addOval(Rect(Offset(200f,200f),100f))
        }

        val pathOp = Path().apply {
            op(squareWithoutOp,circle, PathOperation.Difference)
        }

        drawPath(
            path = squareWithoutOp,
            color = Color.Red,
            style = Stroke(2.dp.toPx())
        )
        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(2.dp.toPx())
        )

        drawPath(
            path = pathOp,
            color = Color.Green,
          //  style = Stroke(2.dp.toPx())
        )
        val x = 800f
        val y = 200f
        drawPath(
            path = Path().apply {
                moveTo(x,y - 30f)
                lineTo(x - 30f,y + 60f)
                lineTo(x + 30f,y + 60f)
                close()
            },
            color = Color.Green,
            //  style = Stroke(2.dp.toPx())
        )


    }
}