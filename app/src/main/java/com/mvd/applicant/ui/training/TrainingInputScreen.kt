package com.mvd.applicant.ui.training

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue

@Composable
fun TrainingInputScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Подготовка",
            style = MaterialTheme.typography.headlineMedium,
            color = MvdBlue
        )
        Spacer(Modifier.height(16.dp))
        Text("Здесь будет форма ввода данных и генератор программы тренировок.")
    }
}
