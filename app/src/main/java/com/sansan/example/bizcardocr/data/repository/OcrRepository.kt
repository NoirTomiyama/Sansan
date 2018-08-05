package com.sansan.example.bizcardocr.data.repository

import android.util.Base64
import com.sansan.example.bizcardocr.data.network.ApiProvider
import com.sansan.example.bizcardocr.domain.entity.googleapis.vision.*
import kotlinx.coroutines.experimental.async

object OcrRepository {

    suspend fun ocrRequest(rawImage: ByteArray): ImagesResponse? {
        // TODO: 課題2 (rawImageオブジェクトをBase64文字列へ変換してください)

        val encode : String = Base64.encodeToString(rawImage,Base64.DEFAULT)

        val list: MutableList<String> = mutableListOf()

        list.add("ja")
        list.add("en")

        val request = ImagesRequest.createSingleRequest(
                Image(encode),
                Feature("DOCUMENT_TEXT_DETECTION",10),
                ImageContext(list)
        )


        val response = async { ApiProvider.visionApi.images(request).execute() }.await()
        return when (response.code()){
            200 -> response.body()
            else -> null
        }
    }
}