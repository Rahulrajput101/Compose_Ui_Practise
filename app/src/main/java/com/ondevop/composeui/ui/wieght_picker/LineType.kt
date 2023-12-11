package com.ondevop.composeui.ui.wieght_picker

sealed class LineType{
    object Normal: LineType()
    object FiveStep: LineType()
    object TenStep: LineType()
}
