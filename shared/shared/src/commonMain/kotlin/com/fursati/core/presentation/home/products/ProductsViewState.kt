package com.fursati.core.presentation.home.products

data class Product(val id: Int, val name: String)
data class HomeProductResponse(
    val products: List<Product>,
    val status: Boolean,
    val currentPage: Int,
    val totalPages: Int
)

data class AddToFavResponse(
    val status: Boolean,
    val message: String,
)


data class ProductsViewState(
    // TODO
    var homeProducts: HomeProductResponse? = null,
    var addToFav: AddToFavResponse? = null,
)