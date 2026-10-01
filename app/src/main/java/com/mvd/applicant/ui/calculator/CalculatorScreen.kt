package com.mvd.applicant.ui.calculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed
import com.mvd.applicant.ui.widgets.WheelPicker
import kotlin.math.abs

@Composable
fun CalculatorScreen(vm: CalculatorViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    val strengthValues = remember(state.gender) {
        if (state.gender == Gender.MALE) (0..50).toList() else (0..60).toList()
    }
    val strengthLabels = remember(strengthValues) { strengthValues.map { it.toString() } }
    val strengthIndex = strengthValues.indexOf(state.strengthValue).coerceAtLeast(0)

    val run100Values = remember { (100..250).map { it / 10.0 } }
    val run100Labels = remember { run100Values.map { "%.1f".format(it) } }
    val run100Index = run100Values
        .indexOfFirst { abs(it - state.run100Seconds) < 0.001 }
        .coerceAtLeast(0)

    val run1000MinValues = remember { (2..8).toList() }
    val run1000MinLabels = remember { run1000MinValues.map { it.toString() } }
    val run1000MinIndex = run1000MinValues.indexOf(state.run1000Minutes).coerceAtLeast(0)

    val run1000SecValues = remember { (0..59).toList() }
    val run1000SecLabels = remember { run1000SecValues.map { "%02d".format(it) } }
    val run1000SecIndex = run1000SecValues.indexOf(state.run1000Seconds).coerceAtLeast(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Вступительные испытания",
            style = MaterialTheme.typography.headlineMedium,
            color = MvdBlue
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = state.gender == Gender.MALE,
                onClick = { vm.setGender(Gender.MALE) },
                label = { Text("Юноша") }
            )
            FilterChip(
                selected = state.gender == Gender.FEMALE,
                onClick = { vm.setGender(Gender.FEMALE) },
                label = { Text("Девушка") }
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = state.group == PurposeGroup.GROUP_1_2,
                onClick = { vm.setGroup(PurposeGroup.GROUP_1_2) },
                label = { Text("1-2 группа") }
            )
            FilterChip(
                selected = state.group == PurposeGroup.GROUP_3_4,
                onClick = { vm.setGroup(PurposeGroup.GROUP_3_4) },
                label = { Text("3-4 группа") }
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outline)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                val title = if (state.gender == Gender.MALE)
                    "Сила: Подтягивание (раз)"
                else "Сила: СКУ (раз)"
                Text(title, style = MaterialTheme.typography.titleLarge, color = MvdBlue)
                Spacer(Modifier.height(8.dp))
                key(state.gender) {
                    WheelPicker(
                        items = strengthLabels,
                        selectedIndex = strengthIndex,
                        onSelectedIndexChange = { vm.setStrength(strengthValues[it]) }
                    )
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Скорость: Бег 100 м (сек)",
                    style = MaterialTheme.typography.titleLarge, color = MvdBlue)
                Spacer(Modifier.height(8.dp))
                WheelPicker(
                    items = run100Labels,
                    selectedIndex = run100Index,
                    onSelectedIndexChange = { vm.setRun100(run100Values[it]) }
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Выносливость: Бег 1000 м",
                    style = MaterialTheme.typography.titleLarge, color = MvdBlue)
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    WheelPicker(
                        items = run1000MinLabels,
                        selectedIndex = run1000MinIndex,
                        onSelectedIndexChange = { vm.setRun1000Minutes(run1000MinValues[it]) },
                        modifier = Modifier.weight(1f)
                    )
                    Text(":", style = MaterialTheme.typography.headlineMedium, color = MvdBlue)
                    WheelPicker(
                        items = run1000SecLabels,
                        selectedIndex = run1000SecIndex,
                        onSelectedIndexChange = { vm.setRun1000Seconds(run1000SecValues[it]) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Button(
            onClick = vm::calculate,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MvdBlue)
        ) {
            Text("Рассчитать", style = MaterialTheme.typography.titleLarge)
        }

        AnimatedVisibility(
            visible = state.result != null,
            enter = fadeIn(tween(400)) + scaleIn(tween(400), initialScale = 0.9f)
        ) {
            state.result?.let { result ->
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!result.allPassed) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MvdRed.copy(alpha = 0.1f))
                        ) {
                            Text(
                                text = "Норматив не сдан. Минимальный балл за каждое упражнение — 1",
                                color = MvdRed,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                    ResultCard("Сила: ${result.strength.name}", result.strength.value, result.strength.points)
                    ResultCard("Скорость: ${result.speed.name}", result.speed.value, result.speed.points)
                    ResultCard("Выносливость: ${result.endurance.name}", result.endurance.value, result.endurance.points)
                    Card(colors = CardDefaults.cardColors(containerColor = MvdBlue)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Итог", color = Color.White, style = MaterialTheme.typography.titleLarge)
                            Text("${result.total}", color = Color.White, style = MaterialTheme.typography.headlineMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultCard(title: String, value: String, points: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, style = MaterialTheme.typography.bodyLarge)
                Text(value, style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text("$points", style = MaterialTheme.typography.headlineMedium, color = MvdBlue)
        }
    }
}
