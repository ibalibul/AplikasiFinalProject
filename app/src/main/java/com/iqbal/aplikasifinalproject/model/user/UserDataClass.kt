package com.iqbal.aplikasifinalproject.model.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDataClass(
    val email: String,
    val password: String
): Serializable
