package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityDeleteBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Delete : AppCompatActivity() {
    lateinit var binding : ActivityDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var deleteData = intent.getSerializableExtra("data") as Movie
        binding.apply {
            textView.text = deleteData.name
            textView1.text = deleteData.actors
        }
        binding.cancelButton.setOnClickListener {
            var intent2 = Intent(this , MainActivity::class.java)
            startActivity(intent2)
            finish()
        }
        binding.deleteButton.setOnClickListener {
            val itemType = object : TypeToken<List<Movie>>(){}.type
            val gson = Gson()
            var sharedPreferences = getSharedPreferences("MY_KEY" , MODE_PRIVATE)

            var oldData = sharedPreferences.getString("data","none")
            var oldArray : ArrayList<Movie> = gson.fromJson(oldData,itemType)


            oldArray.remove(Movie(deleteData.name , deleteData.actors))
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