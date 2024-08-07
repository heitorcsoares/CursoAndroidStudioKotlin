package com.example.hqawasomeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hqawasomeapp.api.ComicService
import com.example.hqawasomeapp.data.ApiCredentials
import com.example.hqawasomeapp.data.Comic
import com.example.hqawasomeapp.data.DataState
import com.example.hqawasomeapp.data.Event
import com.example.hqawasomeapp.helper.ApiHelper
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQViewModel : ViewModel() {                                   //extensão da classe ViewModel

    /** LiveData - DETAILS */
    val hqDetailsLiveData: LiveData<Comic>
        get() = _hqDetailsLiveData
    private val _hqDetailsLiveData = MutableLiveData<Comic>()

    /** LiveData - LIST */
    val hqListLiveData: LiveData<List<Comic>?>
        get() = _hqListLiveData                                     //Escuta por alteração
    private val _hqListLiveData = MutableLiveData<List<Comic>?>()

    /** Estado do App (Sucesso/ERRO/Carregando) Utilizado no (fragment_item_list.xml) */
    val appState: LiveData<DataState>
        get() =  _appState
    private val _appState = MutableLiveData<DataState>()

    /** LiveData - navigationDETAIL */
    val navigationToDetailLiveData
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Event<Unit>>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicService = retrofit.create(ComicService::class.java)        //Faz a chamada e receber os dados

    /** Executa ao acessar o APP */
    init {
        _appState.postValue(DataState.Loading)                          //configura o Estado (Loading - carregando) ao AppState inicialmente.
        getHqData()
    }

    fun onHQSelected(position: Int) {
        val hqDetails = _hqListLiveData.value?.get(position)
        hqDetails?.let {
            _hqDetailsLiveData.postValue(it)
            _navigationToDetailLiveData.postValue(Event(Unit))
        }
    }

    private fun getHqData(){
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privatekey}${ApiCredentials.publickey}"
        val hash = ApiHelper.generateMD5Hash(input)

        viewModelScope.launch {
            val response = comicService.getComicList(timestamp, ApiCredentials.publickey,hash,100)

            Log.d("API_CALL", "Resposta da API: ${response.code()}")               //Log para LogCat

            if(response.isSuccessful) {
                _hqListLiveData.postValue(response.body()?.data?.results)
                _appState.postValue(DataState.Success)                                      //configura o Estado SUCESSS ao App
            } else {
                Log.d("API_CALL", "Error na requisição: ${response.errorBody()?.string()}")   //Log para LogCat
                _appState.postValue(DataState.Error)                                        //configura o Estado ERRO ao App inicialmente
            }
        }

    }
}