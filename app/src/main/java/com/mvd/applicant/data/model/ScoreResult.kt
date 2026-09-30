package com.mvd.applicant.data.model

data class ExerciseScore(
    val name: String,
    val value: String,
    val points: Int
)

data class ScoreResult(
    val strength: ExerciseScore,
    val speed: ExerciseScore,
    val endurance: ExerciseScore
) {
    val total: Int get() = strength.points + speed.points + endurance.points
    val allPassed: Boolean
        get() = strength.points > 0 && speed.points > 0 && endurance.points > 0
}
