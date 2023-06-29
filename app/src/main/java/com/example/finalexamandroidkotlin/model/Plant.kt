package com.example.finalexamandroidkotlin.model

data class Plant (
    var id: String? = null,
    var name: String? = null,
    var image: String? = null,
    var listTag: MutableList<String>? = null,
    var listHeart: MutableList<String>? = null,
    var point: Double? = null,
    var kingdom: String? = null,
    var categories: Species? = null,
    var description: String? = null
) {
}