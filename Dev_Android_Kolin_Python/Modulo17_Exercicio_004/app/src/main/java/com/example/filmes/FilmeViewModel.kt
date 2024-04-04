package com.example.filmes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.filmesDetalhes.FilmeDetalhes
import com.example.filmes.filmesHome.FilmesService
import com.example.filmes.data.FilmesResponse
import com.example.filmes.data.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FilmeViewModel : ViewModel(){

    /** Estado do App (Sucesso ERRO Carregando) */
    val appState: LiveData<DataState>
        get() =  _appState
    private val _appState = MutableLiveData<DataState>()

    /**Função para carregar conteudo. Tipo [FilmesDetalhes.kt]*/
    fun loadFilmeDetalhes(): FilmeDetalhes {
        return FilmeDetalhes("Meus Filmes", "Este é apenas um conteúdo Fixo!")
    }


    FilmesService.getFilmeList(timestamp,ApiCredentials.publickey,hash,100)
    .enqueue(object: Callback<FilmesResponse> {
        override fun onResponse(
            call: Call<FilmesResponse>,
            response: Response<FilmesResponse>
        ) {
            if(response.isSuccessful) {
                _filmeListLiveData.postValue(response.body()?.data?.result)
                _appState.postValue(DataState.Success)                                      //configura o Estado SUCESSS ao App
            }else{
                _appState.postValue(DataState.Error)                                        //configura o Estado ERRO ao App inicialmente
            }
        }

        override fun onFailure(call: Call<FilmesResponse>, t: Throwable) {
            _appState.postValue(DataState.Error)                                            //configura o Estado ERRO ao App inicialmente
        }

    })
}
}