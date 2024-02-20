package com.example.hqawasomeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hqawasomeapp.HQDetails.HQDetails
import com.example.hqawasomeapp.hqHome.ComicService
import com.example.hqawasomeapp.placeholder.PlaceholderContent
import com.example.hqawasomeapp.ApiCredentials
import com.example.hqawasomeapp.placeholder.PlaceholderContent.PlaceholderItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQViewModel : ViewModel() {

    /** LiveData - DETAILS */
    val hqDetailsLiveData: LiveData<HQDetails>
        get() = _hqDetailsLiveData
    private val _hqDetailsLiveData = MutableLiveData<HQDetails>()

    /** LiveData - LIST */
    val hqListLiveData: LiveData<MutableLiveData<PlaceholderItem>>
        get() = hqListLiveData
    private val _hqListLiveData = MutableLiveData<MutableList<PlaceholderItem>>()

    /** LiveData - navigationDETAIL */
    val navigationToDetailLiveData get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicService = retrofit.create(ComicService::class.java)

    init {
        _hqListLiveData.postValue(PlaceholderContent.ITEMS)
    }

    fun onHQSelected(position: Int) {
        val hqDetails = HQDetails("Minha HQ", "Este é apenas texto")
        _hqDetailsLiveData.postValue(hqDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }

    fun getHqData(){
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privatakey}${ApiCredentials.publickey}"
        val hash = ApiHelper.generateMD5Hash(input)

        comicService.getComicsList(timestamp,ApiCredentials.publickey,hash,100)
    }

}