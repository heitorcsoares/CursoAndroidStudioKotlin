package com.example.hqawasomeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hqawasomeapp.HQDetails.HQDetails
import com.example.hqawasomeapp.hqHome.ComicsService
import com.example.hqawasomeapp.hqHome.HQFragment
import com.example.hqawasomeapp.placeholder.DataState
import com.example.hqawasomeapp.placeholder.PlaceholderContent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HQViewModel : ViewModel() {

    val hqDetailsLiveData: LiveData<HQDetails> get() = _hqDetailsLiveData
    private val _hqDetailsLiveData = MutableLiveData<HQDetails>()

    val hqListLiveData: LiveData<MutableList<PlaceholderContent.PlaceholderItem>> get() = _hqListLiveData
    private val _hqListLiveData = MutableLiveData<MutableList<PlaceholderContent.PlaceholderItem>>()

    val navigationToDetailLiveData get() = _navigationToDetailLiveData

    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicsService = retrofit.create(ComicsService::class.java)

    init {
        _hqListLiveData.postValue(PlaceholderContent.ITEMS)
    }

    fun onHQSelected(process: Int) {
        val hqDetails = HQDetails("Minha HQ", "Este apenas contéudo fixo")
        _hqDetailsLiveData.postValue(hqDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }

    fun getHqData(){
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privatekey}${ApiCredentials.publickey}"
        val hash = ApiHelper.generateMD5Hash(input)

        comicsService.getComicsList(timestamp,ApiCredentials.publickey, hash, 100)

    }

}