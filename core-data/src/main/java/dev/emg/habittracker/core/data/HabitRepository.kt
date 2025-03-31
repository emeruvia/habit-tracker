package dev.emg.habittracker.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.emg.habittracker.core.database.Habit
import dev.emg.habittracker.core.database.HabitDao
import dev.emg.habittracker.core.database.toFormattedString
import java.util.Calendar
import javax.inject.Inject

interface HabitRepository {
    val habits: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultHabitRepository @Inject constructor(
    private val habitDao: HabitDao
) : HabitRepository {

    override val habits: Flow<List<String>> =
        habitDao.getHabits().map { items -> items.map { it.date.toFormattedString() } }

    override suspend fun add(name: String) {
        habitDao.insertHabit(Habit(date = Calendar.getInstance().time))
    }
}
