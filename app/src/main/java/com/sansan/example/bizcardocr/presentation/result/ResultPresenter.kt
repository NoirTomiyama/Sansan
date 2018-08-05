package com.sansan.example.bizcardocr.presentation.result

import com.sansan.example.bizcardocr.domain.usecase.OcrUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ResultPresenter(
        private val resultView: ResultContract.View
): ResultContract.Presenter {

    override fun onStart(rawImage: ByteArray) {
        request(rawImage)
    }

    private fun request(rawImage: ByteArray) {
        launch(CommonPool) {
            val ocrResult = OcrUseCase.bizCardOcr(rawImage)
            launch(UI) {
                // TODO: 課題3 (ResultContract.Viewで定義したメソッドを呼び出し、OCR結果をActivityへ通知してください)
                // ・OCR成功時(is OcrUseCase.OcrResult.Success)の際にはocrResult.bizCardInfoをActivityへ通知してください。
                // ・OCR失敗時はOCRが失敗したことをActivityへ通知してください。
                when(ocrResult) {
                    is OcrUseCase.OcrResult.Success -> TODO("実装してください")
                    is OcrUseCase.OcrResult.RequestError -> TODO("実装してください")
                    is OcrUseCase.OcrResult.NetworkError -> TODO("実装してください")
                }
            }
        }
    }
}
