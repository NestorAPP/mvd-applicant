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
    val strengthInput: String = "",
    val run100Input: String = "",
    val run1000MinInput: String = "",
    val run1000SecInput: String = "",
    val strengthError: String? = null,
    val run100Error: String? = null,
    val run1000Error: String? = null,
    val result: ScoreResult? = null
)

class CalculatorViewModel(
    private val repository: ScoreRepository = ScoreRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(CalculatorUiState())
    val state: StateFlow<CalculatorUiState> = _state.asStateFlow()

    fun setGender(g: Gender) { _state.value = _state.value.copy(gender = g, result = null) }
    fun setGroup(g: PurposeGroup) { _state.value = _state.value.copy(group = g, result = null) }
    fun setStrength(v: String) { _state.value = _state.value.copy(strengthInput = v, strengthError = null) }
    fun setRun100(v: String) { _state.value = _state.value.copy(run100Input = v, run100Error = null) }
    fun setRun1000Min(v: String) { _state.value = _state.value.copy(run1000MinInput = v, run1000Error = null) }
    fun setRun1000Sec(v: String) { _state.value = _state.value.copy(run1000SecInput = v, run1000Error = null) }

    fun calculate() {
        val s = _state.value
        var hasError = false

        val strength = s.strengthInput.toIntOrNull()
        val run100 = s.run100Input.replace(',', '.').toDoubleOrNull()
        val run1000Min = s.run1000MinInput.toIntOrNull()
        val run1000Sec = s.run1000SecInput.toIntOrNull()

        if (strength == null || strength < 0) {
            _state.value = _state.value.copy(strengthError = "Введите число")
            hasError = true
        }
        if (run100 == null || run100 < 10.0 || run100 > 30.0) {
            _state.value = _state.value.copy(run100Error = "От 10.0 до 30.0")
            hasError = true
        }
        if (run1000Min == null || run1000Sec == null || run1000Sec !in 0..59) {
            _state.value = _state.value.copy(run1000Error = "Проверьте время")
            hasError = true
        }

        if (hasError) return

        val result = repository.calculate(
            gender = s.gender,
            group = s.group,
            strengthValue = strength!!,
            run100Seconds = run100!!,
            run1000Minutes = run1000Min!!,
            run1000Seconds = run1000Sec!!
        )
        _state.value = _state.value.copy(result = result)
    }
}
