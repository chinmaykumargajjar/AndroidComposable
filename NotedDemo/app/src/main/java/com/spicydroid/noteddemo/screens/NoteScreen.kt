package com.spicydroid.noteddemo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotesScreen(viewModel : NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notes.collectAsState()
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(value= content , // use the content
            onValueChange= {content = it}, //we are assigning to content
            modifier =Modifier
                .fillMaxSize()
                .padding(8.dp))
    }

    Button(onClick = {
        if(content.isNotBlank()) {
            viewModel.addNote(content)
            content = ""
        }
    }) {
        Text("Add Note")
    }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(notes) { note ->
            Text(
                text = note.content,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clickable {
                        viewModel.deleteNote(note)
                    }
            )
        }
    }
}