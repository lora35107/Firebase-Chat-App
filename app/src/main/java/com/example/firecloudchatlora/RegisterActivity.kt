package com.example.firecloudchatlora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var EditName :EditText
    private lateinit var EditEmail :EditText
    private lateinit var EditPassword:EditText
    private lateinit var CreateAccountButton :Button

    //Initialise firebase
    private lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        EditName =findViewById(R.id.edt_name_reg)
        EditEmail = findViewById(R.id.edt_email_reg)
        EditPassword = findViewById(R.id.edt_pass_reg)
        CreateAccountButton = findViewById(R.id.btn_account)

        // Initialise firebase again
        Auth = FirebaseAuth.getInstance()

        CreateAccountButton.setOnClickListener {
            val name =EditName.text.toString().trim()
            val email = EditEmail.text.toString().trim()
            val password =EditPassword.text.toString().trim()

            //Validate inputs
             if (name.isEmpty()||email.isEmpty()||password.isEmpty()){
                 Toast.makeText(this, "One of the fields is empty :(", Toast.LENGTH_SHORT).show()
             }else{
                 //Create a user in firebase
                 Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                     if (it.isSuccessful){
                         Toast.makeText(this, "User created Successfully!", Toast.LENGTH_SHORT).show()

                     }else{
                         Toast.makeText(this, "Failed to create Account", Toast.LENGTH_SHORT).show()
                     }
                 }

             }
        }
    }
}