package com.ondevop.composeui.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


