package com.spicydroid.noteddemo.repository

import com.spicydroid.noteddemo.data.NoteDao
import com.spicydroid.noteddemo.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao : NoteDao) {
    suspend fun getAllNotes() = noteDao.getAllNotes()
    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun delete(note: Note) = noteDao.delete(note)
}