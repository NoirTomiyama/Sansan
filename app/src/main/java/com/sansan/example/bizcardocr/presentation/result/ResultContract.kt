package com.sansan.example.bizcardocr.presentation.result

interface ResultContract {
    // TODO: 課題3 (ResultContract.Viewを実装してResultActivityへOCR結果を通知する仕組みを実装してください)
    // 必要なInterfaceは最低限、次の2種類です。
    // 1. OCR成功時にOCR結果をViewへ通知するイベント
    //    (Viewでは通知が来た際にはRecyclerViewへOCR結果の反映を行います)
    // 2. OCR失敗時にViewへ通知するイベント
    // 　 (Viewでは通知が来た際にはToastの表示、およびRecyclerViewへの反映を行います)
    interface View {
    }

    interface Presenter {
        fun onStart(rawImage: ByteArray)
    }
}
