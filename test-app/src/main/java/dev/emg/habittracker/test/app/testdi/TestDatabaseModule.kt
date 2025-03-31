package dev.emg.habittracker.test.app.testdi

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.emg.habittracker.core.data.HabitRepository
import dev.emg.habittracker.core.data.di.DataModule
import dev.emg.habittracker.core.data.di.FakeHabitRepository

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface FakeDataModule {

    @Binds
    abstract fun bindRepository(
        fakeRepository: FakeHabitRepository
    ): HabitRepository
}
