package dev.emg.habittracker.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import dev.emg.habittracker.core.data.HabitRepository
import dev.emg.habittracker.core.data.DefaultHabitRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsHabitRepository(
        habitRepository: DefaultHabitRepository
    ): HabitRepository
}

class FakeHabitRepository @Inject constructor() : HabitRepository {
    override val habits: Flow<List<String>> = flowOf(fakeHabits)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeHabits = listOf("One", "Two", "Three")
