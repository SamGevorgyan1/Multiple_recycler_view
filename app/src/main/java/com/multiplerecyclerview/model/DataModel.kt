package com.multiplerecyclerview.model

sealed class DataModel {


    data class ImageUrl(
        val imageUrl: String

    ):DataModel()

    data class TextPost(
        val textPost: String
    ):DataModel()

    data class WebViewPost(
        val webImgUrl:String,
        val webViewUrl:String
    ):DataModel()



}