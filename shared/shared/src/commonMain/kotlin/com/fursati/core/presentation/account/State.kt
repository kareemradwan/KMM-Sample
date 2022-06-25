package com.fursati.core.presentation.account

data class State<T>(
    var response: T? = null,
    var exception: String? = null,
    var loading: Boolean = false
)