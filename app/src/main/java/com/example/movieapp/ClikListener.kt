package com.example.movieapp

import android.view.View

interface ClikListener {
        fun onClikEdit(movie : Movie)
        fun onClikItem(movie : Movie)
        fun onClikDelete(movie : Movie)
    }
