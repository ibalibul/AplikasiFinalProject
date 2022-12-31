package com.iqbal.aplikasifinalproject.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iqbal.aplikasifinalproject.datastore.UserPrefs
import com.iqbal.aplikasifinalproject.model.user.UserDataClass
import com.iqbal.aplikasifinalproject.model.user.UserResponse
import com.iqbal.aplikasifinalproject.network.ResfulAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(var api : ResfulAPI, @ApplicationContext appContext: Context): ViewModel() {

    private val userStore: UserPrefs = UserPrefs(appContext)
    fun saveData(token_: String, name: String) {
        GlobalScope.launch {
            userStore.setToken(token_, name)
        }
    }
    fun deleteToken() {
        GlobalScope.launch {
            userStore.deleteToken()
        }
    }
    fun getToken()= userStore.getToken().asLiveData()
    fun getName()= userStore.getName().asLiveData()


    var login : MutableLiveData<UserResponse?> = MutableLiveData()
    fun LoginLive() : LiveData<UserResponse?> {
        return login
    }

    //Retrofit
    fun apiLogin(email: String, pass: String){
        api.authLogin(UserDataClass(email,pass))
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body!=null){
                            login.postValue(body)
                            Log.d("SUCCESS", "$body")
                        }else{
                            login.postValue(null)
                            error("NULL" + response.message())
                        }
                    }else{
                        login.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    login.postValue(null)
                    Log.e("FAILED", "SOMETHING WRONG", t )
                }

            })
    }
}