package com.jana.greenkeeper.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(context: Context) : ViewModel() {

    private val fAuthInstance: FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser?
        get() = fAuthInstance.currentUser

    var isLoggedIn by mutableStateOf(currentUser != null)
        private set

    private val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    init {
        isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun signIn(context: Context, email: String, password: String) {
        fAuthInstance.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    isLoggedIn = true
                    sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                } else {
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    isLoggedIn = false
                }
            }
    }

    fun register(context: Context, email: String, password: String) {
        fAuthInstance.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logout() {
        fAuthInstance.signOut()
        isLoggedIn = false
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }
}