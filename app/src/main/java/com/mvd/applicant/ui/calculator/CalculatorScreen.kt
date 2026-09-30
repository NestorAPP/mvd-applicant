package com.mvd.applicant.ui.calculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed

@Composable
fun CalculatorScreen(vm: CalculatorViewModel = viewModel()) {
    val state by vm.state.collectAsState()

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

        val strengthLabel = if (state.gender == Gender.MALE) "Подтягивание (раз)" else "СКУ (раз)"
        OutlinedTextField(
            value = state.strengthInput,
            onValueChange = vm::setStrength,
            label = { Text(strengthLabel) },
            isError = state.strengthError != null,
            supportingText = { state.strengthError?.let { Text(it, color = MvdRed) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.run100Input,
            onValueChange = vm::setRun100,
            label = { Text("Бег 100 м (сек)") },
            isError = state.run100Error != null,
            supportingText = { state.run100Error?.let { Text(it, color = MvdRed) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = state.run1000MinInput,
                onValueChange = vm::setRun1000Min,
                label = { Text("1000 м — мин") },
                isError = state.run1000Error != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = state.run1000SecInput,
                onValueChange = vm::setRun1000Sec,
                label = { Text("1000 м — сек") },
                isError = state.run1000Error != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
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
                Text(value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text("$points", style = MaterialTheme.typography.headlineMedium, color = MvdBlue)
        }
    }
}
