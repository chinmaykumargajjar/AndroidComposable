package com.spicydroid.NirvanaReader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.spicydroid.NirvanaReader.navigation.ReaderNavigation
import com.spicydroid.NirvanaReader.ui.theme.NirvanaReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = "Jeo"
        user["lastName"] = "James"

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("TAG", "onCreate: $it.id")
            }.addOnFailureListener{
                Log.d("TAG", "onFailure: $it.id")
            }
        setContent {
            NirvanaReaderTheme {
                ReaderApp()
            }
        }
    }

}

@Composable
fun ReaderApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 46.dp)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ReaderNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NirvanaReaderTheme {
        //Greeting("Android")
    }
}