package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityInfoBinding

class Info : AppCompatActivity() {
    lateinit var binding : ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var data = intent.getSerializableExtra("data") as Movie
        binding.textView2.text = data.name
        binding.textView4.text = data.actors

        binding.closeButton.setOnClickListener{
            finish()
        }

    }
}