package com.example.hqawasomeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hqawasomeapp.hqDetails.HQDetails
import com.example.hqawasomeapp.hqHome.ComicService
import com.example.hqawasomeapp.data.Comic
import com.example.hqawasomeapp.data.ComicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HQViewModel : ViewModel() {

    /** LiveData - DETAILS */
    val hqDetailsLiveData: LiveData<HQDetails>
        get() = _hqDetailsLiveData
    private val _hqDetailsLiveData = MutableLiveData<HQDetails>()

    /** LiveData - LIST */
    val hqListLiveData: LiveData<List<Comic>?>
        get() = _hqListLiveData
    private val _hqListLiveData = MutableLiveData<List<Comic>?>()

    /** LiveData - navigationDETAIL */
    val navigationToDetailLiveData
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val comicService = retrofit.create(ComicService::class.java)

    init {
        getHqData()
    }

    fun onHQSelected(position: Int) {
        val hqDetails = HQDetails("Minha HQ", "Este é apenas texto")
        _hqDetailsLiveData.postValue(hqDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }

    private fun getHqData(){
        val timestamp = ApiHelper.getCurrentTimeStamp()
        val input = "$timestamp${ApiCredentials.privatekey}${ApiCredentials.publickey}"
        val hash = ApiHelper.generateMD5Hash(input)

        comicService.getComicList(timestamp,ApiCredentials.publickey,hash,100)
            .enqueue(object: Callback<ComicResponse>{
                override fun onResponse(
                    call: Call<ComicResponse>,
                    response: Response<ComicResponse>
                ) {
                    if(response.isSuccessful) {
                        _hqListLiveData.postValue(response.body()?.data?.result)
                    }
                }

                override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}