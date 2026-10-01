package com.mvd.applicant.ui.calculator

import androidx.lifecycle.ViewModel
import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup
import com.mvd.applicant.data.model.ScoreResult
import com.mvd.applicant.data.repository.ScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CalculatorUiState(
    val gender: Gender = Gender.MALE,
    val group: PurposeGroup = PurposeGroup.GROUP_1_2,
    val strengthValue: Int = 10,
    val run100Seconds: Double = 15.0,
    val run1000Minutes: Int = 4,
    val run1000Seconds: Int = 30,
    val result: ScoreResult? = null
)

class CalculatorViewModel(
    private val repository: ScoreRepository = ScoreRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(CalculatorUiState())
    val state: StateFlow<CalculatorUiState> = _state.asStateFlow()

    fun setGender(g: Gender) {
        val defaultStrength = if (g == Gender.MALE) 10 else 30
        _state.value = _state.value.copy(
            gender = g,
            strengthValue = defaultStrength,
            result = null
        )
    }

    fun setGroup(g: PurposeGroup) {
        _state.value = _state.value.copy(group = g, result = null)
    }

    fun setStrength(v: Int) {
        _state.value = _state.value.copy(strengthValue = v, result = null)
    }

    fun setRun100(v: Double) {
        _state.value = _state.value.copy(run100Seconds = v, result = null)
    }

    fun setRun1000Minutes(v: Int) {
        _state.value = _state.value.copy(run1000Minutes = v, result = null)
    }

    fun setRun1000Seconds(v: Int) {
        _state.value = _state.value.copy(run1000Seconds = v, result = null)
    }

    fun calculate() {
        val s = _state.value
        val result = repository.calculate(
            gender = s.gender,
            group = s.group,
            strengthValue = s.strengthValue,
            run100Seconds = s.run100Seconds,
            run1000Minutes = s.run1000Minutes,
            run1000Seconds = s.run1000Seconds
        )
        _state.value = _state.value.copy(result = result)
    }
}
