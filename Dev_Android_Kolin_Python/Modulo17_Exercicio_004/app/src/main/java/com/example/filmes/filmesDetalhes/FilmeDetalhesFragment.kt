package com.example.filmes.filmesDetalhes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.example.filmes.viewModel.FilmeViewModel
import com.example.filmes.R
import com.example.filmes.databinding.FragmentFilmeDetalhesBinding

class FilmeDetalhesFragment : Fragment() {

    private val viewModel by navGraphViewModels<FilmeViewModel>(R.id.filmes_graph) {defaultViewModelProviderFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFilmeDetalhesBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_filme_detalhes,
                container,
                false
            )

        binding.lifecycleOwner = this
        binding.filmeDetalhes = viewModel.loadFilmeDetalhes()

        return binding.root
    }
}