package com.example.filmes.filmesHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.filmes.R
import com.example.filmes.data.Filmes
import com.example.filmes.databinding.FragmentItemBinding

/** Mantem a (Interface) depois dos (import) */
interface FilmeItemListener {
    fun onItemSelected(position: Int)
}

class MyfilmeRecyclerViewAdapter(
    private val listener: FilmeItemListener                                //Adiciona ouvinte como um parâmetro do construtor
) : RecyclerView.Adapter<MyfilmeRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Filmes> = ArrayList()

    fun updateData(filmeList: List<Filmes>){
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

        holder.bindItem(item)           /** Chama função (bindItem->Inner class|MyhqRecyclerViewAdapter.kt) */

        /** ação para escutar click */
        holder.view.setOnClickListener {
            listener.onItemSelected(position)

            /** Inicie a ação de navegação para a tela de detalhes
            holder.view.findNavController().navigate(R.id.action_filmeItemFragment_to_filmeDetalhesFragment)
             */
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val view = binding.root

        fun bindItem(item: Filmes){
            binding.filmeItem = item                        //filmeItem-> Variavel (fragment_item | Data)
            binding.executePendingBindings()
        }
    }
}