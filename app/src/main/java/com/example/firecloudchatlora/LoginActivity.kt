package com.example.firecloudchatlora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var EmailLogin : EditText
    private lateinit var PasswordLogin :EditText
    private lateinit var LoginButton :Button
    private lateinit var RegisterButton :Button
    private lateinit var Auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        EmailLogin =findViewById(R.id.edt_email_login)
        PasswordLogin = findViewById(R.id.edt_password_login)
        LoginButton = findViewById(R.id.btn_login_login)
        RegisterButton = findViewById(R.id.btn_reg_login)
        Auth = FirebaseAuth.getInstance()

        LoginButton.setOnClickListener {
            val email = EmailLogin.text.toString().trim()
            val password = EmailLogin.text.toString().trim()

            //validate input
            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "One of the fields is empty :(", Toast.LENGTH_SHORT).show()
            }else{
                Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login success :)", Toast.LENGTH_SHORT).show()

                    val GoToMain = Intent(this,MainActivity::class.java)
                    startActivity(GoToMain)
                    finish()
                }else {
                        Toast.makeText(this, "Login Failed :(", Toast.LENGTH_SHORT).show()
                }
                }
            }
        }
        RegisterButton.setOnClickListener {
            val gotoreg = Intent(this,RegisterActivity::class.java)
            startActivity(gotoreg)
            finish()

        }
    }
}