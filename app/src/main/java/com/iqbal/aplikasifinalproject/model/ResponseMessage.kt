package com.iqbal.aplikasifinalproject.model


import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)