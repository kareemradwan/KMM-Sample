package com.fursati.core.presentation._utils

sealed class DataState<out T>(
    val data: T? = null,
    val exception: String? = null,
    val empty: Boolean = false,
    val loading: Boolean? = null,
) {


    class Success<T>(data: T) : DataState<T>(data = data)
    class Loading(loading: Boolean) : DataState<Nothing>(loading = loading)
    class Error(exception: String) : DataState<Nothing>(exception = exception)


}