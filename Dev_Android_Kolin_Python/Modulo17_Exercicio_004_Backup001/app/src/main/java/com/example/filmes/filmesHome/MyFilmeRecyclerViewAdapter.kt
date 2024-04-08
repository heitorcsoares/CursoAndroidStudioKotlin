package com.example.filmes.filmesHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmes.data.FilmesLatest
import com.example.filmes.databinding.FragmentItemBinding

/** Mantem a (Interface) depois dos (import) */
interface FilmeItemListener {
    fun onItemSelected(position: Int)
}

class MyfilmeRecyclerViewAdapter(
    private val listener: FilmeItemListener
) : RecyclerView.Adapter<MyfilmeRecyclerViewAdapter.ViewHolder>() {

    private var values: List<FilmesLatest> = ArrayList()

    fun updateData(filmeList: List<FilmesLatest>){
        values = filmeList
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

        fun bindItem(item: FilmesLatest) {
            binding.filmeItem = item
            binding.executePendingBindings()                                            //Atualiza os dados no momento.
        }
    }
}