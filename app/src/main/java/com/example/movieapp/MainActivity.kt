package com.example.movieapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var arrayList = ArrayList<Movie>()
    val itemType = object : TypeToken<List<Movie>>(){}.type
    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var sharedPreferences = getSharedPreferences("MY_KEY" , MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (sharedPreferences.getString("data","none") == "none"){

        }
        else{
            var oldData : ArrayList<Movie> = gson.fromJson(sharedPreferences.getString("data","none"),itemType)
            arrayList.addAll(oldData)
        }

        var myAdapter = RvAdapter(arrayList , object : ClikListener {
            override fun onClikEdit(movie: Movie) {
                var intent = Intent(this@MainActivity , Edit::class.java)
                intent.putExtra("data",movie)
                startActivity(intent)
                finish()
            }

            override fun onClikItem(movie: Movie) {
                var intent = Intent(this@MainActivity , Info::class.java)
                intent.putExtra("data",movie)
                startActivity(intent)

            }

            override fun onClikDelete(movie: Movie) {
                var intent = Intent(this@MainActivity , Delete::class.java)
                intent.putExtra("data",movie)
                startActivity(intent)
                finish()
            }
        })
        binding.RecycleView.adapter = myAdapter

        binding.addButton.setOnClickListener{
            var intent = Intent(this , Add::class.java)
            startActivity(intent)
            finish()
        }

    }

//    override fun onResume() {
//        super.onResume()
//        var sharedPreferences = getSharedPreferences("MY_KEY" , MODE_PRIVATE)
//        if (sharedPreferences.getString("data","none") == "none"){
//            Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            var oldData : ArrayList<Movie> = gson.fromJson(sharedPreferences.getString("data","none"),itemType)
//            arrayList.addAll(oldData)
//        }
//
//        var myAdapter = RvAdapter(arrayList , object : ClikListener {
//            override fun onClikEdit(movie: Movie) {
//                var intent = Intent(this@MainActivity , Edit::class.java)
//                intent.putExtra("data",movie)
//                startActivity(intent)
//                finish()
//            }
//        })
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.d("123123", "onresume")
//        if (intent.getSerializableExtra("newData")!=null) {
//
//            var editetData = intent.getSerializableExtra("newData") as EditMovie
//            arrayList.forEach {
//                if (it.name == editetData.oldName && it.actors == editetData.oldActors) {
//                    it.name = editetData.newName
//                    it.actors = editetData.newActors
//                }
//            }
//            Toast.makeText(this, editetData.toString(), Toast.LENGTH_SHORT).show()
//        }
//        arrayList.add(Movie("onRes moments of spring", "i dont know"))
//
//        var myAdapter = RvAdapter(arrayList , object : ClikListener {
//            override fun onClikEdit(movie: Movie) {
//                var intent = Intent(this@MainActivity , Edit::class.java)
//                intent.putExtra("data",movie)
//                startActivity(intent)
//                finish()
//            }
//        })
//        binding.RecycleView.adapter = myAdapter
//
//
//    }


}