package com.example.hqawasomeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hqawasomeapp.HQDetails.HQDetails
import com.example.hqawasomeapp.hqHome.HQFragment
import com.example.hqawasomeapp.placeholder.DataState
import com.example.hqawasomeapp.placeholder.PlaceholderContent


class HQViewModel : ViewModel() {

    /** LiveData - Criação de Variavel - Title */
    private val _hqTitleLiveData = MutableLiveData<HQFragment>()
    val hqTitleLiveData: LiveData<HQFragment>
        get() = _hqTitleLiveData

    /** LiveData - Criação de Variavel - Details */
    private val _hqDetailsLiveData = MutableLiveData<HQDetails>()
    val hqDetailsLiveData: LiveData<HQDetails>
        get() = _hqDetailsLiveData

    /** LiveData - Criação de Variavel - STATE */
    private val _dataStateListLiveData = MutableLiveData<DataState<List<PlaceholderContent.PlaceholderItem>>>()
    val dataStateListLiveData: LiveData<DataState<List<PlaceholderContent.PlaceholderItem>>>
        get() = _dataStateListLiveData

    private val _dataStateDetailsLiveData = MutableLiveData<DataState<HQDetails>>()
    val dataStateDetailsLiveData: LiveData<DataState<HQDetails>>
        get() = _dataStateDetailsLiveData


    /**Função para carregar conteudo. Tipo [HQDetails.kt]*/
    fun loadHQDetails(): HQDetails {
        return HQDetails("Minha HQ", "Este apenas contéudo fixo")
    }


}