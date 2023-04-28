package com.example.firecloudchatlora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
     private lateinit var CarMake :EditText
     private lateinit var CarModel :EditText
     private lateinit var CarPrice :EditText
     private lateinit var btnSaveData :Button
     private lateinit var BtnCarPhoto :Button
     private lateinit var BtnViewCars :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CarMake = findViewById(R.id.edt_carname)
        CarModel = findViewById(R.id.edt_carmodel)
        CarPrice = findViewById(R.id.edt_carprice)
        BtnCarPhoto = findViewById(R.id.btn_carphoto)
        BtnViewCars = findViewById(R.id.btn_viewcars)
        btnSaveData = findViewById(R.id.btn_save)

        //initialise Firebase
        val database = FirebaseDatabase.getInstance()
        val databaseRef = database.getReference("cars")


        btnSaveData.setOnClickListener {
            val Make = CarMake.toString().trim()
            val Model = CarModel.toString().trim()
            val Price = CarPrice.toString().trim()
            //Never ever trust a user :)
            //So we validate the edittexts

            if (Make.isEmpty()||Model.isEmpty()||Price.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty form", Toast.LENGTH_SHORT).show()
            }else{
                //Saving info kwa database "db"
                val usercar = Car(Make,Model,Price)
                val ref = FirebaseDatabase.getInstance().getReference().child("cars")

                ref.setValue(usercar).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Car data uploaded successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Failed to cave car info", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }

        BtnCarPhoto.setOnClickListener {  }
        BtnViewCars.setOnClickListener {  }
    }
}