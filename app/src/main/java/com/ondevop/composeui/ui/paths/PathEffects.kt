package com.ondevop.composeui.ui.paths

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp


@Composable
fun PathEffects(){

    val infiniteTransition = rememberInfiniteTransition(label = "Infinite")

    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10000f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing)
        ), label = ""
    )


    Canvas(modifier = Modifier.fillMaxSize()) {

        val path = Path().apply {
            moveTo(0f,-100f)
            cubicTo(
//                100f,
//                300f,
//                600f,
//                700f,
//                100f,
//                900f
                100f,900f,600f,700f,100f,900f
            )
        }

//        drawPath(
//            path = path,
//            color = Color.Red,
//            style = Stroke(
//                width = 5.dp.toPx(),
//                pathEffect = PathEffect.dashPathEffect(
//                    intervals = floatArrayOf(50f,30f),
//                    phase = phase
//                )
//
//            )
//        )
        val squareWithoutOp = Path().apply{
            addRect(Rect(Offset(400f,0f), Size(200f,200f)))
        }

        val circle = Path().apply {
            addOval(Rect(Offset(200f,200f),100f))
        }

        val pathOp = Path().apply {
            op(squareWithoutOp,circle, PathOperation.Difference)
        }
        val balloon = Path().apply {
            val width = 150f // Increase width to elongate the balloon
            val height = 200f // Increase height to elongate the balloon
            val radius = 20f // Adjust this value to change the roundness of the balloon

            // Start at the top center
            moveTo(width / 2, 0f)

            // Draw the top right curve
            cubicTo(width * 0.75f, 0f, width, height / 12, width, height / 6)

            // Draw the bottom right curve
            cubicTo(width, height * 0.25f, width - (width / 12), height * 0.5f, width - radius, height / 2)

            // Draw the bottom left curve
            cubicTo(width / 2, height - (height / 12), width / 4, height * 0.5f, radius, height / 2)

            // Draw the top left curve
            cubicTo(0f, height * 0.25f, width / 12, 0f, 0f, height / 6)

            // Close the path
            close()
        }

        rotate(degrees = 45f, pivot = Offset(0f,200f)){
             drawPath(
                 path =  path,
                 color = Color.Red,
                 style = Stroke(
                     width = 5.dp.toPx(),
                     pathEffect = PathEffect.stampedPathEffect(
                         shape = balloon,
                         advance = 750f,
                         phase = phase,
                         style = StampedPathEffectStyle.Translate
                     )
                 ),
             )
         }




//        val oval = Path().apply {
//            addOval(Rect(Offset(0f,0f),100f))
//        }
//        drawPath(
//            path = path,
//            color = Color.Red,
//            style = Stroke(
//                width = 5.dp.toPx(),
//                pathEffect = PathEffect.stampedPathEffect(
//                    shape = oval,
//                    advance = 750f,
//                    phase = phase,
//                    style = StampedPathEffectStyle.Translate
//                )
//
//            )
//        )


    }
}