package com.iqbal.aplikasifinalproject.model


import com.google.gson.annotations.SerializedName

data class GetResponsesDataUserItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)