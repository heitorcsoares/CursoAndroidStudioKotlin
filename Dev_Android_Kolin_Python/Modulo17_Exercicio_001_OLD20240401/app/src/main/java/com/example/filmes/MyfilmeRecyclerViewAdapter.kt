package com.example.filmes

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.filmes.placeholder.PlaceholderContent.PlaceholderItem
import com.example.filmes.databinding.FragmentItemBinding

/** Mantem a (Interface) depois dos (import) */
interface FilmeItemListener {
    fun onItemSelected(position: Int)
}

class MyfilmeRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val listener: FilmeItemListener,                    //Adiciona ouvinte como um parâmetro do construtor
    private val fragment: Fragment
) : RecyclerView.Adapter<MyfilmeRecyclerViewAdapter.ViewHolder>() {

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
        holder.itemView.setOnClickListener {
            listener.onItemSelected(position)

            /** Inicie a ação de navegação para a tela de detalhes */
            holder.itemView.findNavController().navigate(R.id.action_filmeItemFragment_to_filmeDetalhesFragment)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: PlaceholderItem){
            binding.filmeItem = item                //filmeItem-> Variavel (fragment_item | Data)
            binding.executePendingBindings()
        }
    }
}