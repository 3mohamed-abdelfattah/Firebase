package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth

class Register : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        binding.signInButton.setOnClickListener {

            auth.createUserWithEmailAndPassword(
                binding.email.text.toString().trim(),
                binding.password.text.toString(),
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            baseContext,
                            "Sign Up Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        val errorCode =
                            (task.exception as FirebaseAuthException).errorCode.replace("_", " ")
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.\n$errorCode",
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                }
        }
    }
}