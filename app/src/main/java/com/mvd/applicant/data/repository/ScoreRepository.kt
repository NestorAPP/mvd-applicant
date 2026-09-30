package com.mvd.applicant.data.repository

import com.mvd.applicant.data.model.*
import com.mvd.applicant.data.tables.ScoreTables

class ScoreRepository {

    fun calculate(
        gender: Gender,
        group: PurposeGroup,
        strengthValue: Int,
        run100Seconds: Double,
        run1000Minutes: Int,
        run1000Seconds: Int
    ): ScoreResult {
        val totalRun1000Sec = run1000Minutes * 60 + run1000Seconds

        val strengthPoints = when (gender) {
            Gender.MALE -> ScoreTables.scorePullups(strengthValue, group)
            Gender.FEMALE -> ScoreTables.scoreSku(strengthValue, group)
        }

        val speedPoints = ScoreTables.scoreRun100(run100Seconds, gender, group)
        val endurancePoints = ScoreTables.scoreRun1000(totalRun1000Sec, gender, group)

        val strengthName = if (gender == Gender.MALE) "Подтягивание" else "СКУ"
        val strengthValueStr = "$strengthValue раз"

        return ScoreResult(
            strength = ExerciseScore(strengthName, strengthValueStr, strengthPoints),
            speed = ExerciseScore("Бег 100 м", "%.1f сек".format(run100Seconds), speedPoints),
            endurance = ExerciseScore(
                "Бег 1000 м",
                "%d:%02d".format(run1000Minutes, run1000Seconds),
                endurancePoints
            )
        )
    }
}
