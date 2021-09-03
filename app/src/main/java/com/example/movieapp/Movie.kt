package com.example.movieapp

import java.io.Serializable

data class Movie(
    var name:String,
    var actors : String
) : Serializable
