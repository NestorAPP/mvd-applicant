package com.mvd.applicant.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed

@Composable
fun TricolorStripe(
    modifier: Modifier = Modifier,
    height: Dp = 10.dp
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .shadow(2.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFF2F2F2))
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MvdBlue)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MvdRed)
        )
    }
}
