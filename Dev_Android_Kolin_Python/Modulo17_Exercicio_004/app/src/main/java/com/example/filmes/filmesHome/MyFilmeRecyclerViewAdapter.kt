package com.example.filmes.filmesHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.filmes.R
import com.example.filmes.data.FilmeLatest
import com.example.filmes.placeholder.PlaceholderContent.PlaceholderItem
import com.example.filmes.databinding.FragmentItemBinding

/** Mantem a (Interface) depois dos (import) */
interface FilmeItemListener {
    fun onItemSelected(position: Int)
}

class MyfilmeRecyclerViewAdapter(
    private val listener: FilmeItemListener
) : RecyclerView.Adapter<MyfilmeRecyclerViewAdapter.ViewHolder>() {

    private var values: List<FilmeLatest> = ArrayList()

    fun updateData(FilmeLatest: List<FilmeLatest>){
        values = FilmeLatest
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.bindItem(item)

        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val view = binding.root

        fun bindItem(item: FilmeLatest) {
            binding.filmeItem = item
            binding.executePendingBindings()
        }
    }
}