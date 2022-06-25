package com.fursati.core.data.model.account.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(

    @SerialName("status")
    var status: Boolean? = null,
    @SerialName("message")

    var message: String? = null,
    @SerialName("user")

    var user: User? = null,

    )


@Serializable
data class User(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("role")
    var role: String? = null,
)