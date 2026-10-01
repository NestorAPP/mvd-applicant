package com.mvd.applicant.ui.technique

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            title = "Подтягивание на перекладине (юноши)",
            description = """
Из виса хватом сверху с выпрямленными руками, туловищем и ногами по команде «Начинай!», сгибая руки, подтянуться, подняв подбородок выше грифа перекладины, затем опуститься в вис, зафиксировать на 0,5 секунды неподвижное положение и продолжить выполнение упражнения.

Разрешается незначительное сгибание и разведение ног, незначительное отклонение тела от вертикального положения.

Касание ногами опоры, выполнение рывковых и маховых движений запрещается.

Принимающий упражнение объявляет счёт каждого законченного движения. Объявление счёта одновременно является разрешением на продолжение упражнения. В случае нарушения правил выполнения упражнения вместо очередного счёта подаётся команда «Не считать!». Если эта команда подаётся трижды подряд, выполнение упражнения прекращается.
            """.trimIndent()
        )

        TechniqueCard(
            title = "СКУ — силовое комплексное упражнение (девушки)",
            description = """
Выполняется по команде «Начинай!» в течение одной минуты:

• первые 30 секунд — из положения лёжа на спине (ноги не зафиксированы, пятки касаются пола), руки вдоль туловища, ладони параллельно полу, выполнить максимальное количество наклонов вперёд до касания пальцев ног руками (допускается незначительное сгибание ног в коленных суставах, при возвращении в исходное положение необходимо касание пола лопатками);

• затем, без паузы для отдыха, по команде «Смена!», следующие 30 секунд — из положения упор лёжа выполнить максимальное количество сгибаний и разгибаний рук (расстояние между руками — по ширине плеч, кисти вперёд, локти разведены не более чем на 45 градусов относительно туловища, туловище прямое, руки сгибать до касания грудью пола).

Принимающий упражнение объявляет счёт каждого законченного движения. В случае нарушения правил выполнения упражнения вместо очередного счёта подаётся команда «Не считать!». По истечении времени, отведённого на выполнение упражнения, подаётся команда «Стой!».

При определении итогового результата суммируются засчитанные повторения наклонов вперёд и сгибаний и разгибаний рук. В случае отсутствия засчитанных движений в наклонах вперёд и (или) сгибании и разгибании рук проверяемому выставляется отметка «0 баллов» за упражнение в целом.
            """.trimIndent()
        )

        TechniqueCard(
            title = "Бег 100 метров",
            description = """
Проводится на стадионе или прямом участке асфальтированной дороги по командам: «На старт!», «Внимание!», «Марш!».

При наличии возможности устанавливаются стартовые колодки.

Хронометраж прекращается, когда бегущий пересёк линию финиша любой частью туловища.

Результат определяется с точностью до 0,1 секунды.
            """.trimIndent()
        )

        TechniqueCard(
            title = "Бег (кросс) 1000 метров",
            description = """
Выполняется на ровном участке или по пересечённой местности.

С высокого старта по командам: «На старт!», «Марш!».

Хронометраж прекращается, когда бегущий пересёк линию финиша любой частью туловища.

Результат определяется с точностью до 1 секунды.
            """.trimIndent()
        )
    }
}

@Composable
private fun TechniqueCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = MvdBlue)
            Spacer(Modifier.height(8.dp))
            Text(description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
