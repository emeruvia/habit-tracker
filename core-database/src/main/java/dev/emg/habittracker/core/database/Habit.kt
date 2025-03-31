package dev.emg.habittracker.core.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date
)

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit ORDER BY id DESC LIMIT 10")
    fun getHabits(): Flow<List<Habit>>

    @Insert
    suspend fun insertHabit(item: Habit)
}
