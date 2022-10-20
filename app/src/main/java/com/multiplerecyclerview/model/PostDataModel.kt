package com.multiplerecyclerview.model

sealed class PostDataModel {

    data class ImagePost(
        val imagePostUrl: String
    ):PostDataModel()

    data class TextPost(
        val textPost: String
    ):PostDataModel()

    data class WebViewPost(
        val webImgUrl:String,
        val webViewUrl:String
    ):PostDataModel()



}