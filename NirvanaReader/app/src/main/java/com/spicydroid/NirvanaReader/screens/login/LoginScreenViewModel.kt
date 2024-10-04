package com.spicydroid.NirvanaReader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.spicydroid.NirvanaReader.model.MUser
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    //val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email : String , password : String , home : () -> Unit)
            = viewModelScope.launch {
        if(_loading.value == false) {
            _loading.value = true

            try {
                auth.createUserWithEmailAndPassword(email , password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val displayName = task.result.user!!.email!!.split("@")?.get(0)
                            createUser(displayName)
                            home()
                        } else {
                            Log.d(
                                "TAG" ,
                                "createUserWithEmailAndPassword: ${task.result.toString()}"
                            )
                        }
                        _loading.value = false;
                    }
            } catch (ex : Exception) {
                Log.d("TAG" , "createUserWithEmailAndPassword: ${ex.message}")
            }
        }
    }

    private fun createUser(displayName : String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(
            id = userId,
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer"
        ).toMap()

       // val user = mutableMapOf<String, Any>()
        user["user_id"] = userId.toString()
        user["display_name"] = displayName.toString()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)


    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        if(_loading.value == false) {
            _loading.value = true
            try {
                auth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(
                                "TAG" ,
                                "signInWithEmailAndPassword Yayyy: ${task.result.toString()}"
                            )
                            home()
                        } else {
                            Log.d("TAG" , "signInWithEmailAndPassword: ${task.result.toString()}")
                        }
                        _loading.value = false
                    }
            } catch (ex : Exception) {
                Log.d("TAG" , "signInWithEmailPass: ${ex.message}")
            }
        }
    }
}