package com.mvd.applicant.data.tables

import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup

/**
 * Таблицы баллов для абитуриентов (приказ МВД России № 44 от 02.02.2024, п. 343.4).
 *
 * Логика:
 *  - «Больше — лучше» (подтягивание, СКУ): первая строка, где результат >= порога.
 *  - «Меньше — лучше» (бег 100 м, бег 1000 м): первая строка, где время <= порога.
 *  - Прочерки пропускаются.
 *  - Ниже минимального порога — 0 баллов.
 *  - Максимум: 33 + 33 + 34 = 100 баллов.
 */
object ScoreTables {

    // ============================================================
    // 1-2 ГРУППА ПРЕДНАЗНАЧЕНИЯ
    // ============================================================

    private val pullupsMale_1_2: Map<Int, Int> = mapOf(
        33 to 30, 32 to 29, 31 to 28, 30 to 27, 29 to 26,
        28 to 25, 27 to 24, 26 to 23, 25 to 22, 24 to 21,
        23 to 20, 22 to 19, 21 to 18, 20 to 17, 19 to 16,
        18 to 15, 17 to 14, 16 to 13, 15 to 12, 14 to 11,
        13 to 10, 12 to 9, 10 to 8, 8 to 7, 6 to 6,
        4 to 5, 1 to 4
    )

    private val skuFemale_1_2: Map<Int, Int> = mapOf(
        33 to 50, 32 to 49, 31 to 48, 30 to 47, 29 to 46,
        28 to 45, 27 to 44, 26 to 43, 25 to 42, 24 to 41,
        23 to 40, 22 to 39, 21 to 38, 20 to 37, 19 to 36,
        18 to 35, 17 to 34, 16 to 33, 15 to 32, 14 to 31,
        13 to 30, 11 to 29, 10 to 28, 9 to 27, 8 to 26,
        7 to 25, 6 to 24, 5 to 23, 4 to 22, 3 to 21,
        2 to 20, 1 to 19
    )

    private val run100Male_1_2: Map<Int, Double> = mapOf(
        33 to 12.8, 32 to 12.9, 31 to 13.0, 30 to 13.1, 29 to 13.2,
        28 to 13.3, 27 to 13.4, 26 to 13.5, 25 to 13.6, 24 to 13.7,
        23 to 13.8, 22 to 13.9, 21 to 14.0, 20 to 14.1, 19 to 14.2,
        18 to 14.3, 17 to 14.4, 16 to 14.5, 15 to 14.6, 14 to 14.7,
        13 to 14.8, 11 to 14.9, 10 to 15.0, 9 to 15.1, 8 to 15.2,
        7 to 15.3, 6 to 15.4, 5 to 15.5, 4 to 15.6, 3 to 15.7,
        2 to 15.8, 1 to 15.9
    )

    private val run100Female_1_2: Map<Int, Double> = mapOf(
        33 to 15.0, 32 to 15.1, 31 to 15.2, 30 to 15.3, 29 to 15.4,
        28 to 15.5, 27 to 15.6, 26 to 15.7, 25 to 15.8, 24 to 15.9,
        23 to 16.0, 22 to 16.1, 21 to 16.2, 20 to 16.3, 19 to 16.4,
        18 to 16.5, 17 to 16.6, 16 to 16.7, 15 to 16.8, 14 to 16.9,
        13 to 17.0, 12 to 17.1, 11 to 17.2, 10 to 17.3, 9 to 17.4,
        8 to 17.5, 7 to 17.6, 6 to 17.7, 5 to 17.8, 4 to 17.9,
        3 to 18.0, 2 to 18.1, 1 to 18.2
    )

    private val run1000Male_1_2: Map<Int, Int> = mapOf(
        34 to 225, 33 to 226, 32 to 227, 31 to 228, 30 to 229,
        29 to 230, 28 to 231, 27 to 232, 26 to 233, 25 to 234,
        24 to 235, 23 to 236, 22 to 237, 21 to 238, 20 to 239,
        19 to 240, 18 to 241, 17 to 242, 16 to 243, 15 to 244,
        14 to 245, 13 to 246, 12 to 247, 11 to 248, 10 to 249,
        9 to 250, 8 to 251, 7 to 252, 6 to 253, 5 to 254,
        4 to 255, 3 to 256, 2 to 257, 1 to 258
    )

    private val run1000Female_1_2: Map<Int, Int> = mapOf(
        34 to 242, 33 to 243, 32 to 244, 31 to 245, 30 to 246,
        29 to 247, 28 to 248, 27 to 249, 26 to 250, 25 to 251,
        24 to 252, 23 to 253, 22 to 254, 21 to 255, 20 to 256,
        19 to 257, 18 to 258, 17 to 259, 16 to 260, 15 to 261,
        14 to 262, 13 to 263, 12 to 264, 11 to 265, 10 to 266,
        9 to 267, 8 to 268, 7 to 269, 6 to 270, 5 to 271,
        4 to 272, 3 to 273, 2 to 274, 1 to 275
    )

    // ============================================================
    // 3-4 ГРУППА ПРЕДНАЗНАЧЕНИЯ
    // ============================================================

    private val pullupsMale_3_4: Map<Int, Int> = mapOf(
        33 to 29, 32 to 28, 31 to 27, 30 to 26, 29 to 25,
        28 to 24, 27 to 23, 26 to 22, 25 to 21, 24 to 20,
        23 to 19, 22 to 18, 21 to 17, 20 to 16, 19 to 15,
        18 to 14, 17 to 13, 16 to 12, 15 to 11, 14 to 10,
        13 to 9, 12 to 8, 10 to 7, 8 to 6, 6 to 5,
        4 to 4, 2 to 3, 1 to 2
    )

    private val skuFemale_3_4: Map<Int, Int> = mapOf(
        33 to 46, 32 to 45, 30 to 44, 28 to 43, 26 to 42,
        25 to 41, 24 to 40, 23 to 39, 22 to 38, 21 to 37,
        20 to 36, 19 to 35, 18 to 34, 17 to 33, 16 to 32,
        15 to 31, 14 to 30, 13 to 29, 12 to 28, 11 to 27,
        10 to 26, 9 to 25, 8 to 24, 7 to 23, 6 to 22,
        5 to 21, 4 to 20, 3 to 19, 2 to 18, 1 to 17
    )

    private val run100Male_3_4: Map<Int, Double> = mapOf(
        33 to 13.2, 32 to 13.3, 31 to 13.4, 30 to 13.5, 29 to 13.6,
        28 to 13.7, 27 to 13.8, 26 to 13.9, 25 to 14.0, 24 to 14.1,
        23 to 14.2, 22 to 14.3, 21 to 14.4, 20 to 14.5, 19 to 14.6,
        18 to 14.7, 17 to 14.8, 16 to 14.9, 15 to 15.0, 14 to 15.1,
        13 to 15.2, 12 to 15.3, 11 to 15.4, 10 to 15.5, 9 to 15.6,
        8 to 15.7, 7 to 15.8, 6 to 15.9, 5 to 16.0, 4 to 16.1,
        3 to 16.2, 2 to 16.3, 1 to 16.4
    )

    private val run100Female_3_4: Map<Int, Double> = mapOf(
        33 to 15.4, 32 to 15.5, 31 to 15.6, 30 to 15.7, 29 to 15.8,
        28 to 15.9, 27 to 16.0, 26 to 16.1, 25 to 16.2, 24 to 16.3,
        23 to 16.4, 22 to 16.5, 21 to 16.6, 20 to 16.7, 19 to 16.8,
        18 to 16.9, 17 to 17.0, 16 to 17.1, 15 to 17.2, 14 to 17.3,
        13 to 17.4, 12 to 17.5, 11 to 17.6, 10 to 17.7, 9 to 17.8,
        8 to 17.9, 7 to 18.0, 6 to 18.1, 5 to 18.2, 4 to 18.3,
        3 to 18.4, 2 to 18.5, 1 to 18.6
    )

    private val run1000Male_3_4: Map<Int, Int> = mapOf(
        34 to 235, 33 to 236, 32 to 237, 31 to 238, 30 to 239,
        29 to 240, 28 to 241, 27 to 242, 26 to 243, 25 to 244,
        24 to 245, 23 to 246, 22 to 247, 21 to 248, 20 to 249,
        19 to 250, 18 to 251, 17 to 252, 16 to 253, 15 to 254,
        14 to 255, 13 to 256, 12 to 257, 11 to 258, 10 to 259,
        9 to 260, 8 to 261, 7 to 262, 6 to 263, 5 to 264,
        4 to 265, 3 to 266, 2 to 267, 1 to 268
    )

    private val run1000Female_3_4: Map<Int, Int> = mapOf(
        34 to 267, 33 to 268, 32 to 269, 31 to 270, 30 to 271,
        29 to 272, 28 to 273, 27 to 274, 26 to 275, 25 to 276,
        24 to 277, 23 to 278, 22 to 279, 21 to 280, 20 to 281,
        19 to 282, 18 to 283, 17 to 284, 16 to 285, 15 to 286,
        14 to 287, 13 to 288, 12 to 289, 11 to 290, 10 to 291,
        9 to 292, 8 to 293, 7 to 294, 6 to 295, 5 to 296,
        4 to 297, 3 to 298, 2 to 299, 1 to 300
    )

    // ============================================================
    // ФУНКЦИИ РАСЧЁТА
    // ============================================================

    fun scorePullups(count: Int, group: PurposeGroup): Int {
        val table = if (group == PurposeGroup.GROUP_1_2) pullupsMale_1_2 else pullupsMale_3_4
        return table.entries
            .sortedByDescending { it.key }
            .firstOrNull { count >= it.value }
            ?.key ?: 0
    }

    fun scoreSku(count: Int, group: PurposeGroup): Int {
        val table = if (group == PurposeGroup.GROUP_1_2) skuFemale_1_2 else skuFemale_3_4
        return table.entries
            .sortedByDescending { it.key }
            .firstOrNull { count >= it.value }
            ?.key ?: 0
    }

    fun scoreRun100(seconds: Double, gender: Gender, group: PurposeGroup): Int {
        val table = when {
            gender == Gender.MALE && group == PurposeGroup.GROUP_1_2 -> run100Male_1_2
            gender == Gender.MALE && group == PurposeGroup.GROUP_3_4 -> run100Male_3_4
            gender == Gender.FEMALE && group == PurposeGroup.GROUP_1_2 -> run100Female_1_2
            else -> run100Female_3_4
        }
        return table.entries
            .sortedByDescending { it.key }
            .firstOrNull { seconds <= it.value }
            ?.key ?: 0
    }

    fun scoreRun1000(totalSeconds: Int, gender: Gender, group: PurposeGroup): Int {
        val table = when {
            gender == Gender.MALE && group == PurposeGroup.GROUP_1_2 -> run1000Male_1_2
            gender == Gender.MALE && group == PurposeGroup.GROUP_3_4 -> run1000Male_3_4
            gender == Gender.FEMALE && group == PurposeGroup.GROUP_1_2 -> run1000Female_1_2
            else -> run1000Female_3_4
        }
        return table.entries
            .sortedByDescending { it.key }
            .firstOrNull { totalSeconds <= it.value }
            ?.key ?: 0
    }
}
