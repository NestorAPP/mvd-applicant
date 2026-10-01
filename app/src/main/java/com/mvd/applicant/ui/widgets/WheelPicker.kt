package com.mvd.applicant.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdTextSecondary
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.abs

@Composable
fun WheelPicker(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    visibleCount: Int = 3,
    itemHeight: Dp = 44.dp
) {
    if (items.isEmpty()) return

    val visible = if (visibleCount % 2 == 0) visibleCount + 1 else visibleCount
    val halfVisible = visible / 2

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = selectedIndex.coerceIn(0, items.lastIndex)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val centeredIndex by remember {
        derivedStateOf {
            val info = listState.layoutInfo
            val viewportCenter = (info.viewportStartOffset + info.viewportEndOffset) / 2
            val centerItem = info.visibleItemsInfo.minByOrNull { item ->
                abs(item.offset + item.size / 2 - viewportCenter)
            }
            ((centerItem?.index ?: halfVisible) - halfVisible)
                .coerceIn(0, items.lastIndex)
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { centeredIndex }
            .distinctUntilChanged()
            .collect { idx ->
                if (idx in items.indices) onSelectedIndexChange(idx)
            }
    }

    Box(
        modifier = modifier.height(itemHeight * visible),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(itemHeight)
                .background(
                    color = MvdBlue.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(8.dp)
                )
        )
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.fillMaxSize()
        ) {
            items(halfVisible) { Spacer(Modifier.height(itemHeight)) }
            items(items.size) { index ->
                val isSelected = index == centeredIndex
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = items[index],
                        style = if (isSelected) MaterialTheme.typography.titleLarge
                        else MaterialTheme.typography.bodyLarge,
                        color = if (isSelected) MvdBlue else MvdTextSecondary.copy(alpha = 0.5f),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
            items(halfVisible) { Spacer(Modifier.height(itemHeight)) }
        }
    }
}
