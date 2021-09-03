package com.example.movieapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.databinding.ActivityEditBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Edit : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemType = object : TypeToken<List<Movie>>(){}.type
        val gson = Gson()
        var sharedPreferences = getSharedPreferences("MY_KEY" , MODE_PRIVATE)
        var editData = intent.getSerializableExtra("data") as Movie

        binding.editText3.setText(editData.name)
        binding.editText4.setText(editData.actors)
        //Toast.makeText(this, editData.toString(), Toast.LENGTH_SHORT).show()

        binding.saveButton.setOnClickListener{

            var oldData = sharedPreferences.getString("data","none")
            var oldArray : ArrayList<Movie> = gson.fromJson(oldData,itemType)
            oldArray.forEach{
                if (it.name == editData.name && it.actors == editData.actors){
                    it.name = binding.editText3.text.toString()
                    it.actors = binding.editText4.text.toString()
                }
            }
            var json = gson.toJson(oldArray)
            sharedPreferences.edit().putString("data" , json).apply()
            var intent1 = Intent(this , MainActivity::class.java)
            startActivity(intent1)
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}