package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding

class RvAdapter(var list : ArrayList<Movie> , var clikListener: ClikListener ) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {


    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        var binding = ItemBinding.bind(itemView)

        fun onBind(movie : Movie){
            binding.apply {
                movieName.text = movie.name
                movieDescription.text = movie.actors
            }
                binding.editButton.setOnClickListener {
                    clikListener.onClikEdit(movie)
                }
                binding.root.setOnClickListener {
                    clikListener.onClikItem(movie)
                }
                binding.deleteButton.setOnClickListener {
                    clikListener.onClikDelete(movie)
                }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

