package com.mvd.applicant.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed

@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            color = MvdBlue
        )
        Box(
            Modifier
                .width(60.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MvdRed)
        )
    }
}

@Composable
fun SubsectionTitle(text: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MvdBlue
        )
        Box(
            Modifier
                .width(32.dp)
                .height(3.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MvdRed)
        )
    }
}
