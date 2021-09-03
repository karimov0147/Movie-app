package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.example.movieapp.databinding.ActivityAddBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Add : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemType = object : TypeToken<List<Movie>>(){}.type
        val gson = Gson()
        var arrayList = ArrayList<Movie>()
        var sharedPreferences = getSharedPreferences("MY_KEY" , MODE_PRIVATE)

        binding.button.setOnClickListener{
            if (sharedPreferences.getString("data","none")=="none"){
                arrayList.add(Movie(binding.editText.text.toString(),binding.editText2.text.toString()))
                var json = gson.toJson(arrayList)
                sharedPreferences.edit().putString("data" , json).apply()
                var intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                var oldData = sharedPreferences.getString("data","none")
                var oldArray : ArrayList<Movie> = gson.fromJson(oldData,itemType)
                arrayList.add(Movie(binding.editText.text.toString(),binding.editText2.text.toString()))
                arrayList.addAll(oldArray)
                var json = gson.toJson(arrayList)
                sharedPreferences.edit().putString("data" , json).apply()
                var intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()
            }





        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}