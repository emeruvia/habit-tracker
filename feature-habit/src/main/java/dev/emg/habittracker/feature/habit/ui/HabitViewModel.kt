package dev.emg.habittracker.feature.habit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dev.emg.habittracker.core.data.HabitRepository
import dev.emg.habittracker.feature.habit.ui.HabitUiState.Error
import dev.emg.habittracker.feature.habit.ui.HabitUiState.Loading
import dev.emg.habittracker.feature.habit.ui.HabitUiState.Success
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

    val uiState: StateFlow<HabitUiState> = habitRepository
        .habits.map<List<String>, HabitUiState> { Success(data = it) }
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addHabit(name: String) {
        viewModelScope.launch {
            habitRepository.add(name)
        }
    }
}

sealed interface HabitUiState {
    object Loading : HabitUiState
    data class Error(val throwable: Throwable) : HabitUiState
    data class Success(val data: List<String>) : HabitUiState
}
