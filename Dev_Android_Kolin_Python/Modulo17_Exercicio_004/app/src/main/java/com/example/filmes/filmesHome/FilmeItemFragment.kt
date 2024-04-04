package com.example.filmes.filmesHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmes.FilmeViewModel
import com.example.filmes.R
import com.example.filmes.databinding.FragmentItemListBinding
import com.example.filmes.placeholder.PlaceholderContent

class FilmeItemFragment : Fragment(), FilmeItemListener {

    private lateinit var adapter: MyfilmeRecyclerViewAdapter
    private val viewModel by navGraphViewModels<FilmeViewModel>(R.id.filmes_graph){defaultViewModelProviderFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemListBinding.inflate(inflater)

        val view = binding.root
        val recyclerView = binding.list

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MyfilmeRecyclerViewAdapter(this)

        recyclerView.apply {
            this.adapter = this@FilmeItemFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        Toast.makeText(context, "Bem Vindo!", Toast.LENGTH_SHORT).show()

        initObservers()

        return view
    }

    private fun initObservers() {
        viewModel.filmeListLiveData.observe(viewLifecycleOwner, Observer {

            it?.let{
                adapter.updateData(it)
            }
        })

        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner, Observer{
            val action = FilmeDirections.action_filmetemFragment_to_filmeDetalhesFragment()
            findNavController().navigate(action)
        })
    }

    /** Função original (MyfilmeRecyclerViewAdapter | interface)  */
    override fun onItemSelected(position: Int) {
        viewModel.onFilmeSelected(position)
    }
}