package com.iqbal.aplikasifinalproject.network


import binar.academy.flightgoadmin.model.tiket.TiketResponse
import com.iqbal.aplikasifinalproject.model.user.Data
import com.iqbal.aplikasifinalproject.model.user.UserDataClass
import com.iqbal.aplikasifinalproject.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ResfulAPI {

    @GET("ticket")
    fun getAllTic() : Call<TiketResponse>


    @POST("login")
    fun authLogin(@Body login: UserDataClass): Call<UserResponse>

}