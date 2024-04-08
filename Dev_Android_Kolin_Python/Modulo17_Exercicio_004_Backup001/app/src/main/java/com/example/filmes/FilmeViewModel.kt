package com.example.filmes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmes.data.DataState
import com.example.filmes.data.FilmesLatest
import com.example.filmes.filmesDetalhes.FilmeDetalhes
import com.example.filmes.data.FilmesResponse
import com.example.filmes.filmesHome.FilmesService
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class FilmeViewModel : ViewModel() {

    /** LiveData - DETAILS */
    val filmeDetalhesLiveData: LiveData<FilmeDetalhes>
        get() = _filmeDetalhesLiveData
    private val _filmeDetalhesLiveData = MutableLiveData<FilmeDetalhes>()

    /** LiveData - LISTA */
    val filmesListaLiveData: LiveData<List<FilmesLatest>?>
        get() = _filmesListaLiveData
    private val _filmesListaLiveData = MutableLiveData<List<FilmesLatest>?>()

    /** Estado do App (Sucesso ERRO Carregando) */
    val appState: LiveData<DataState>
        get() =  _appState
    private val _appState = MutableLiveData<DataState>()

    /** LiveData - RESPONSE */
    val filmesResponse: LiveData<List<FilmesResponse>?>
        get() = _filmesResponseLiveData                                              //Escuta por alteração
    private val _filmesResponseLiveData = MutableLiveData<List<FilmesResponse>?>()

    /** LiveData - navigationDETAIL */
    val navigationToDetalhesLiveData
        get() = _navigationToDetalhesLiveData
    private val _navigationToDetalhesLiveData = MutableLiveData<Unit>()

    /**  ***************************************************************************** */

    /** Instancia do RETROFIT - acesso e credenciais para API */
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val filmesService = retrofit.create(FilmesService::class.java)
    /**  */

    /** Executa o acesso ao API */
    init {
        getFilmesData()
    }


    fun onFilmeSelected(position: Int) {
        val filmeDetalhes = FilmeDetalhes("Minha HQ", "Este é apenas texto")
        _filmeDetalhesLiveData.postValue(filmeDetalhes)
        _navigationToDetalhesLiveData.postValue(Unit)
    }


    /**Função para carregar conteudo. Tipo [FilmesDetalhes.kt]*/
    fun loadFilmeDetalhes(): FilmeDetalhes {
        return FilmeDetalhes("Meus Filmes", "Este é apenas um conteúdo Fixo!")
    }


    /**  Função Dados dos Filmes da API - Trata resposta (sucesso/falha) */
    private fun getFilmesData() {
        filmesService.getFilmesLista(ApiCredentials.API_KEY, limit = 100)
            .enqueue(object: Callback<FilmesResponse>{
                override fun onResponse(
                    call: Call<FilmesResponse>,
                    response: Response<FilmesResponse>
                ) {
                    if(response.isSuccessful){
                        _filmesListaLiveData.postValue(response.body()?.)
                        _appState.postValue(DataState.Success)
                    }else{
                        _appState.postValue(DataState.Error)
                    }
                }

                override fun onFailure(call: Call<FilmesResponse>, t: Throwable) {
                    _appState.postValue(DataState.Error)
                }

            })
    }
}