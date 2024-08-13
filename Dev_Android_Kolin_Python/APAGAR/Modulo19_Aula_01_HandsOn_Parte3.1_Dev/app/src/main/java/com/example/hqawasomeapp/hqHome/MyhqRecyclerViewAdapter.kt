package com.example.hqawasomeapp.hqHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.hqawasomeapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.hqawasomeapp.databinding.FragmentItemBinding
import com.example.hqawasomeapp.R
import com.example.hqawasomeapp.data.Comic

/** Mantem a (Interface) depois dos (import) */
interface HQItemListener {
    fun onItemSelected(position: Int)
}

class MyhqRecyclerViewAdapter(
    private val listener: HQItemListener,                                                           //Adiciona ouvinte como um parâmetro do construtor
) : RecyclerView.Adapter<MyhqRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Comic> = ArrayList()

    fun updateData(hqList: List<Comic>){
        values = hqList
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

        holder.bindItem(item)                   /** Chama função (bindItem->Inner class|MyhqRecyclerViewAdapter.kt) */

        holder.itemView.setOnClickListener {
            listener.onItemSelected(position)

            /** Inicie a ação de navegação para a tela de detalhes */
            //holder.itemView.findNavController().navigate(R.id.action_HQFragment_to_HQDetailsFragment)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Comic) {
            binding.hqItem = item
            binding.executePendingBindings()
        }
    }
}