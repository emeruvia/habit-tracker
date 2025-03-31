package dev.emg.habittracker.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import dev.emg.habittracker.core.data.DefaultHabitRepository
import dev.emg.habittracker.core.database.Habit
import dev.emg.habittracker.core.database.HabitDao

/**
 * Unit tests for [DefaultHabitRepository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultHabitRepositoryTest {

    @Test
    fun habits_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultHabitRepository(FakeHabitDao())

        repository.add("Repository")

        assertEquals(repository.habits.first().size, 1)
    }

}

private class FakeHabitDao : HabitDao {

    private val data = mutableListOf<Habit>()

    override fun getHabits(): Flow<List<Habit>> = flow {
        emit(data)
    }

    override suspend fun insertHabit(item: Habit) {
        data.add(0, item)
    }
}
