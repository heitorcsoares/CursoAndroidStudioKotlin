package com.example.filmes.filmesHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmes.data.Filmes
import com.example.filmes.databinding.FragmentFilmeItemBinding

/** Mantem a (Interface) depois dos (import) */
interface FilmeItemListener {
    fun onItemSelected(position: Int)
}

class MyFilmeRecyclerViewAdapter(
    private val listener: FilmeItemListener
) : RecyclerView.Adapter<MyFilmeRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Filmes> = ArrayList()

    fun updateData(filmeList: List<Filmes>){
        values = filmeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentFilmeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.bindItem(item)           /** Chama função (bindItem->Inner class|MyhqRecyclerViewAdapter.kt) */

        /** ação para escutar click */
        holder.view.setOnClickListener {
            listener.onItemSelected(position)

        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentFilmeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val view = binding.root

        fun bindItem(item: Filmes){
            binding.filmeItem = item                        //filmeItem-> Variavel (fragment_item | Data)
            binding.executePendingBindings()
        }
    }
}