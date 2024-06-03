package com.jana.greenkeeper.viewmodel

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel {

    private var fAuthInstance: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    init {
        fAuthInstance = FirebaseAuth.getInstance()
        currentUser = fAuthInstance!!.currentUser
    }

     fun signIn(context: Context, email: String, password: String) {
         fAuthInstance!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Prijavljeno uspješno
                    Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Prijavljivanje neuspješno
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun register(context: Context, email: String, password: String) {
        fAuthInstance!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registracija uspješna
                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Registracija neuspješna
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}