package com.example.registrationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var editTextTextPassword2: EditText
    private lateinit var Registration: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        registerListeners()
    }
    private fun init() {
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2)
        Registration = findViewById(R.id.Registration)

    }
    private fun registerListeners() {

        Registration.setOnClickListener {

            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()
            val repeatpassword = editTextTextPassword2.text.toString()


            if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()){
                Toast.makeText(this,"Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (password != repeatpassword){
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}