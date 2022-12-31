package com.iqbal.aplikasifinalproject.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.academy.flightgoadmin.model.tiket.TiketResponse
import com.iqbal.aplikasifinalproject.network.ResfulAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelPlane @Inject constructor(private var api : ResfulAPI, @ApplicationContext appContext: Context): ViewModel(){

    val getAll : MutableLiveData<TiketResponse?> = MutableLiveData()

    fun getLiveAllTic() : LiveData<TiketResponse?> {
        return getAll
    }

    fun getApiAllTic(){
        api.getAllTic()
            .enqueue(object : Callback<TiketResponse>{
                override fun onResponse(
                    call: Call<TiketResponse>,
                    response: Response<TiketResponse>,
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            getAll.postValue(body)
                            Log.d("SUCCESS GET : ", "$body")
                        }else{
                            getAll.postValue(null)
                            Log.d("FAILED GET", "onResponse: $body")
                        }
                    }
                }

                override fun onFailure(call: Call<TiketResponse>, t: Throwable) {
                    getAll.postValue(null)
                    Log.e("FAILED GET : ", "${t.message}", t)
                }

            })
    }

}