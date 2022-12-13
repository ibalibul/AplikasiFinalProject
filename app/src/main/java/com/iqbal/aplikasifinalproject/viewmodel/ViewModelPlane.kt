package com.iqbal.aplikasifinalproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.aplikasifinalproject.model.ResponseDataFilmItem
import com.iqbal.aplikasifinalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelPlane : ViewModel(){

    lateinit var liveDataPlane : MutableLiveData<List<ResponseDataFilmItem>?>

    init {
        liveDataPlane = MutableLiveData()
    }

    fun getLiveDataplane() : MutableLiveData<List<ResponseDataFilmItem>?> {
        return liveDataPlane
    }

    fun getCallApiplane(){
        RetrofitClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataPlane.postValue(response.body())
                    }else{
                        liveDataPlane.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    liveDataPlane.postValue(null)
                }

            })
    }

}