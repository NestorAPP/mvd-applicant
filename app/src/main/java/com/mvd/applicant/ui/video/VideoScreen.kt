package com.mvd.applicant.ui.video

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue

// ЗАГЛУШКИ — заменить на реальные ссылки ВК Видео
private const val VIDEO_PULLUPS = "https://vk.com/video-000000_000000"
private const val VIDEO_SKU = "https://vk.com/video-000000_000000"

@Composable
fun VideoScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Видео с техникой",
            style = MaterialTheme.typography.headlineMedium,
            color = MvdBlue
        )
        VideoCard(
            title = "Подтягивание (юноши)",
            description = "Правильная техника выполнения на перекладине",
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_PULLUPS)))
            }
        )
        VideoCard(
            title = "СКУ (девушки)",
            description = "Силовое комплексное упражнение",
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_SKU)))
            }
        )
    }
}

@Composable
private fun VideoCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = MvdBlue)
            Spacer(Modifier.height(4.dp))
            Text(description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = MvdBlue)
            ) {
                Text("Смотреть в ВК")
            }
        }
    }
}
