package com.example.filmes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes.api.FilmesService
import com.example.filmes.data.ApiCredentials
import com.example.filmes.data.DataState
import com.example.filmes.data.Event
import com.example.filmes.data.Filmes
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class FilmeViewModel : ViewModel() {

    /** LiveData - DETAILS */
    val filmeDetalhesLiveData: LiveData<Filmes>
        get() = _filmeDetalhesLiveData
    private val _filmeDetalhesLiveData = MutableLiveData<Filmes>()

    /** Estado do App (Sucesso ERRO Carregando) */
    val appState: LiveData<DataState>
        get() =  _appState
    private val _appState = MutableLiveData<DataState>()

    /** LiveData - LISTA */
    val filmesListaLiveData: LiveData<List<Filmes>?>
        get() = _filmesListaLiveData
    private val _filmesListaLiveData = MutableLiveData<List<Filmes>?>()

    /** Carroseu */
    val carouselImagesLiveData: LiveData<List<CarouselItem>?>
        get() = _carouselImagesLiveData
    private val _carouselImagesLiveData = MutableLiveData<List<CarouselItem>?>(null)

    /** LiveData - Navegação para Tela de Detalhes */
    val navigationToDetalhesLiveData
        get() = _navigationToDetalhesLiveData
    private val _navigationToDetalhesLiveData = MutableLiveData<Event<Unit>>()

    /**  ***************************************************************************** */

    val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    /** Instancia do RETROFIT - acesso e credenciais para API */
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    /**  ***************************************************************************** */

    private val filmesService = retrofit.create(FilmesService::class.java)                          //Chama Serviço para API .themoviedb

    /** executa automaticamente sempre que uma nova instância da classe for criada */
    init {
        _appState.postValue(DataState.Loading)
        getFilmesData()
    }

    fun onFilmeSelected(position: Int) {
        val filmesDetails = _filmesListaLiveData.value?.get(position)

        filmesDetails?.let {
            _filmeDetalhesLiveData.postValue(it)
            _navigationToDetalhesLiveData.postValue(Event(Unit))
        }

    }

    /**  função Kotlin usando Retrofit / fazer uma requisição GET */
    private fun getFilmesData() {

        viewModelScope.launch {
            val response = filmesService.getFilmesLista(ApiCredentials.API_KEY)

            if (response.isSuccessful) {
                _filmesListaLiveData.postValue(response.body()?.results)
                _appState.postValue(DataState.Success)
            } else {
                _appState.postValue(DataState.Error)
            }
        }

    }

}