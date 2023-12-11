package com.ondevop.composeui.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ondevop.composeui.R
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun MainScreen() {

    var points by remember {
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Points : $points",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    isTimerRunning = (!isTimerRunning)
                    points = 0
                }
            ) {
                Text(text = if (isTimerRunning) "Reset" else "Start")

            }
            CountdownTimer(
                isTimerRunning = isTimerRunning
            ) {
                isTimerRunning = false

            }


        }
        BallClicker(
            enabled = isTimerRunning
        ) {
            points++
        }

    }

}

@Composable
fun CountdownTimer(
    time: Int = 30000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit

) {
    var curTime by remember {
        mutableStateOf(time)
    }

    LaunchedEffect(key1 = curTime, key2 = isTimerRunning) {

        if (!isTimerRunning) {
            curTime = time
            return@LaunchedEffect
        }
        if (curTime > 0) {
            delay(1000L)
            curTime -= 1000
        } else {
            onTimerEnd()
        }

    }

    Text(
        text = (curTime / 1000).toString(),
        fontSize = 20.sp
    )

}

@Composable
fun BallClicker(
    enabled: Boolean,
    radius: Float = 100f,
    ballColor: Color = Color.Red,
    onBallClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        var ballPosition by remember {
            mutableStateOf(
                randomOffSet(
                    radius = radius,
                    width = constraints.maxWidth,
                    height = constraints.maxHeight
                )
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(key1 = enabled) {
                    if (!enabled) {
                        return@pointerInput
                    }
                    detectTapGestures {
                        /**
                         * Generating the distance with help of pythagoras theorem
                         * 'dis = square root of a square plus b square' then we can compare
                         * and get to know the touch is inside the circe or outside the circle
                         */
                        var distance = sqrt(
                            (it.x - ballPosition.x).pow(2) + (it.y - ballPosition.y).pow(2)
                        )

                        if (distance <= radius) {
                            //Randomizing the value because it clicked
                            ballPosition = randomOffSet(
                                radius,
                                constraints.maxWidth,
                                constraints.maxHeight
                            )
                            onBallClick()

                        }
                    }
                }
        ) {
            drawCircle(
                radius = radius,
                color = ballColor,
                center = ballPosition

            )
        }
    }

}

private fun randomOffSet(radius: Float, width: Int, height: Int): Offset {
    val x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat()
    val y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
    return Offset(x, y)
}

@Composable
fun CustomRightClick(
    modifier: Modifier = Modifier,
    circleColor: Color
){
    val scale = remember{
        Animatable(20f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 66.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(200)
        scale.animateTo(
            targetValue = 40f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
    }

    Box(
        modifier = Modifier
            .size(scale.value.dp)
            .background(
                color = circleColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "right",
            modifier = Modifier
                .padding(5.dp)
                .size(scale.value.dp)
        )
    }
    




//    val anim1 = rememberInfiniteTransition(label = "")
//    val anim2 = rememberInfiniteTransition(label = "")
//    val anim3 = rememberInfiniteTransition(label = "")
//
//    var canvasHeight by remember {
//        mutableIntStateOf(0)
//    }
//
//    val radius1 by anim1.animateFloat(initialValue = canvasHeight / 4f, targetValue = canvasHeight / 2f, animationSpec = infiniteRepeatable(
//        initialStartOffset = StartOffset(0),
//        animation = keyframes {
//            durationMillis = 1200
//            canvasHeight / 4f at 0
//            canvasHeight / 2f at 600
//            canvasHeight / 4f at 1200
//        }
//    ), label = "")
//
//    val radius2 by anim2.animateFloat(initialValue = canvasHeight / 4f, targetValue = canvasHeight / 2f, animationSpec = infiniteRepeatable(
//        initialStartOffset = StartOffset(200),
//        animation = keyframes {
//            durationMillis = 1200
//            canvasHeight / 4f at 0
//            canvasHeight / 2f at 600
//            canvasHeight / 4f at 1200
//        }
//    ), label = "")
//
//    val radius3 by anim3.animateFloat(initialValue = canvasHeight / 4f, targetValue = canvasHeight / 2f, animationSpec = infiniteRepeatable(
//        initialStartOffset = StartOffset(400),
//        animation = keyframes {
//            durationMillis = 1200
//            canvasHeight / 4f at 0
//            canvasHeight / 2f at 600
//            canvasHeight / 4f at 1200
//        }
//    ), label = "")
//
//    Canvas(
//        modifier = modifier
//            .width(110.dp)
//            .height(32.dp)
//            .onSizeChanged {
//                canvasHeight = it.height
//            }
//    ) {
//
//        drawCircle(
//            color = Color.White,
//            center = center.copy(x = (size.width / 4) * 2),
//            radius = radius2
//        )
//
//
//    }


}


