package dev.emg.habittracker.feature.habit.ui.habit


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import dev.emg.habittracker.core.data.HabitRepository
import dev.emg.habittracker.feature.habit.ui.HabitUiState
import dev.emg.habittracker.feature.habit.ui.HabitViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class HabitViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = HabitViewModel(FakeHabitRepository())
        assertEquals(viewModel.uiState.first(), HabitUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = HabitViewModel(FakeHabitRepository())
        assertEquals(viewModel.uiState.first(), HabitUiState.Loading)
    }
}

private class FakeHabitRepository : HabitRepository {

    private val data = mutableListOf<String>()

    override val habits: Flow<List<String>>
        get() = flow { emit(data.toList()) }

    override suspend fun add(name: String) {
        data.add(0, name)
    }
}
