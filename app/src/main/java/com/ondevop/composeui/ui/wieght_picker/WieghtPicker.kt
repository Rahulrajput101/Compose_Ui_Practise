package com.ondevop.composeui.ui.wieght_picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeightPicker(){

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        Scale(
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
        ){

        }

    }

}