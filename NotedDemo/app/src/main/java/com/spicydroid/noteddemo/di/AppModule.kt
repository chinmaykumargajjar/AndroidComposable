package com.spicydroid.noteddemo.di

import android.content.Context
import androidx.room.Room
import com.spicydroid.noteddemo.data.NoteDao
import com.spicydroid.noteddemo.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(database : NoteDatabase):NoteDao {
        return database.noteDao()
    }
}