package com.example.filmes

import androidx.lifecycle.ViewModel
import com.example.filmes.filmesDetalhes.FilmeDetalhes

class FilmeViewModel : ViewModel(){

    /**Função para carregar conteudo. Tipo [FilmesDetalhes.kt]*/
    fun loadFilmeDetalhes(): FilmeDetalhes {
        return FilmeDetalhes("Meus Filmes", "Este é apenas um conteúdo Fixo!")
    }
}