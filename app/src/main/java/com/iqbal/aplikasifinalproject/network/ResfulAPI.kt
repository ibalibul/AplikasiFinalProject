package com.iqbal.aplikasifinalproject.network


import com.iqbal.aplikasifinalproject.model.DataUser
import com.iqbal.aplikasifinalproject.model.GetResponsesDataUserItem
import com.iqbal.aplikasifinalproject.model.ResponseDataFilmItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ResfulAPI {

    @GET("film")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>

    @POST("user")
    fun addDataUser(@Body user : DataUser) : Call<GetResponsesDataUserItem>
}