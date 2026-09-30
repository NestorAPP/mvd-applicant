package com.mvd.applicant.ui.technique

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvd.applicant.ui.theme.MvdBlue

@Composable
fun TechniqueScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Техника выполнения",
            style = MaterialTheme.typography.headlineMedium,
            color = MvdBlue
        )
        TechniqueCard(
            "Подтягивание на перекладине",
            "Из виса хватом сверху с выпрямленными руками, туловищем и ногами по команде «Начинай!», сгибая руки, подтянуться, подняв подбородок выше грифа перекладины, затем опуститься в вис, зафиксировать на 0,5 секунды неподвижное положение и продолжить выполнение упражнения."
        )
        TechniqueCard(
            "СКУ (силовое комплексное упражнение)",
            "По команде «Начинай!» без пауз для отдыха выполнить: наклоны вперёд из положения лёжа на спине и сгибание-разгибание рук в упоре лёжа."
        )
        TechniqueCard(
            "Бег 100 метров",
            "С низкого или высокого старта по командам: «На старт!», «Внимание!», «Марш!». Хронометраж прекращается, когда бегущий пересечёт линию финиша любой частью туловища."
        )
        TechniqueCard(
            "Бег 1000 метров",
            "С высокого старта по командам: «На старт!», «Марш!». Выполняется на ровном участке или по пересечённой местности. Хронометраж прекращается, когда бегущий пересечёт линию финиша любой частью туловища."
        )
    }
}

@Composable
private fun TechniqueCard(title: String, description: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = MvdBlue)
            Spacer(Modifier.height(8.dp))
            Text(description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
