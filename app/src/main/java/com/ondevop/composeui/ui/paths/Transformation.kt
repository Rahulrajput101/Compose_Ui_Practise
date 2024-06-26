package com.ondevop.composeui.ui.paths

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.Collections.rotate

//Animation applying
//@Composable
//fun Transfromation(){
//    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Color Animation")
//    val degrees by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 180f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 5000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "Infinite Colors"
//    )
//
//    Canvas(
//        modifier = Modifier.fillMaxSize() ) {
//
//        rotate(degrees = degrees, pivot = Offset(200f,200f)){
//            drawRect(
//                color = Color.Blue,
//                topLeft = Offset(100f,100f),
//                size = Size(200f,200f)
//            )
//        }
//
//    }
//
//}

@Composable
fun Transfromation(){

    Canvas(
        modifier = Modifier.fillMaxSize() ) {

        /**
         * some more traformation are
         * translation : used to move object
         * scale : used to  change the size
         */
//        rotate(degrees = 45f, pivot = Offset(200f,200f)){
//            drawRect(
//                color = Color.Blue,
//                topLeft = Offset(100f,100f),
//                size = Size(200f,200f)
//            )
//        }

        val circle = Path().apply {
            addOval(Rect(Offset(200f,200f),150f))
        }
        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(width = 2.dp.toPx())
        )

        clipPath(
            circle
        ){
            drawRect(
                color = Color.Red,
                topLeft = Offset(200f,200f),
                size = Size(200f,200f)
            )
        }

    }

}