package com.spicydroid.noteddemo.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spicydroid.noteddemo.model.Note
import com.spicydroid.noteddemo.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository : NoteRepository): ViewModel(){

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes

    fun getNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }

    fun addNote(content: String) {
        viewModelScope.launch {
            repository.insert(Note(content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
            getNotes()
        }
    }
}