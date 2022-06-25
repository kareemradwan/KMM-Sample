package com.fursati.core.presentation.home.products

import com.fursati.core.presentation._utils.DataState
import com.fursati.core.presentation.coroutines.ViewModel
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsViewModel(
    var onDataState: ((DataState<ProductsViewState>) -> Unit) = {},
) : ViewModel(), KoinComponent {


    val httpClient: HttpClient by inject()

    private val requests: HashSet<Job> = HashSet()


    fun getProducts(page: Int = 0, size: Int = 1) {
        val job = viewModelScope.launch {
            // TODO Make Http Request :)
            try {
                onDataState(DataState.Loading(true))
                val response = httpClient.get<HomeProductResponse>("") {
                    parameter("page", page)
                    parameter("size", size)
                }
                onDataState(DataState.Success(ProductsViewState(homeProducts = response)))
            } catch (ex: Exception) {
                onDataState(DataState.Error(ex.message ?: "General Error"))
            } finally {
                onDataState(DataState.Loading(false))
            }
        }
        requests.add(job)
    }


    override fun onCleared() {
        super.onCleared()
        cancelRequests()
    }

    public fun cancelRequests() {
        requests.forEach { it.cancel() }
        requests.clear()
    }
}


