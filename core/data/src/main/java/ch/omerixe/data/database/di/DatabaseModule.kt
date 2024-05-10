package ch.omerixe.data.database.di

import android.content.Context
import androidx.room.Room
import ch.omerixe.data.database.OmniDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OmniDatabase {
        return Room.databaseBuilder(context, OmniDatabase::class.java, "omni_database").build()
    }
}