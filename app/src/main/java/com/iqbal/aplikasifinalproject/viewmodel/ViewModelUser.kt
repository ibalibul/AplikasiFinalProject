package com.iqbal.aplikasifinalproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.aplikasifinalproject.model.DataUser
import com.iqbal.aplikasifinalproject.model.GetResponsesDataUserItem
import com.iqbal.aplikasifinalproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {

    lateinit var postLDUser : MutableLiveData<GetResponsesDataUserItem?>

    init {
        postLDUser = MutableLiveData()

    }

    fun postDataUser() : MutableLiveData<GetResponsesDataUserItem?> {
        return postLDUser
    }

    fun callPostApiDataUser(name : String, password : String){
        RetrofitClient.instance.addDataUser(DataUser(name, password))
            .enqueue(object  : Callback<GetResponsesDataUserItem>{
                override fun onResponse(
                    call: Call<GetResponsesDataUserItem>,
                    response: Response<GetResponsesDataUserItem>
                ) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetResponsesDataUserItem>, t: Throwable) {
                    postLDUser.postValue(null)
                }

            })
    }



}