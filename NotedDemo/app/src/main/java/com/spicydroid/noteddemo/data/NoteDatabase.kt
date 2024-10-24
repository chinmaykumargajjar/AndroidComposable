package com.spicydroid.noteddemo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spicydroid.noteddemo.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}