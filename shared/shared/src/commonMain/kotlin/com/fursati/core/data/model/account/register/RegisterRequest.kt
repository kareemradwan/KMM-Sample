package com.fursati.core.data.model.account.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("password_confirmation")
    val password_confirmation: String,
    @SerialName("role")
    val role: String,
    @SerialName("udid")
    val udid: String = "123"
)