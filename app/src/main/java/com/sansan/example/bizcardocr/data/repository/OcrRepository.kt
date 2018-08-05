package com.sansan.example.bizcardocr.data.repository

import com.sansan.example.bizcardocr.data.network.ApiProvider
import com.sansan.example.bizcardocr.domain.entity.googleapis.vision.ImagesRequest
import com.sansan.example.bizcardocr.domain.entity.googleapis.vision.ImagesResponse
import kotlinx.coroutines.experimental.async

object OcrRepository {

    suspend fun ocrRequest(rawImage: ByteArray): ImagesResponse? {
        // TODO: 課題2 (rawImageオブジェクトをBase64文字列へ変換してください)
        val request = ImagesRequest.createSingleRequest()
        val response = async { ApiProvider.visionApi.images(request).execute() }.await()
        return when (response.code()){
            200 -> response.body()
            else -> null
        }
    }
}