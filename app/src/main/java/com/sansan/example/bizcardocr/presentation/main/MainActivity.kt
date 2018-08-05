package com.sansan.example.bizcardocr.presentation.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sansan.example.bizcardocr.R
import com.sansan.example.bizcardocr.presentation.camera.CameraActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener {
            // TODO: 課題1 (Runtime Permissionを実装してカメラ画面、そしてカメラ映像を表示できるようにしてください。)
            // 下記showCameraメソッドはそのまま呼び出すとカメラ画像は表示できるものの、Runtime Permissionが未実装のためカメラ映像を表示することができません。
            // そのためRuntime Permissionを実装し、許可ダイアログで許可を取ってからカメラ画面を表示するように修正してください。
            showCamera()
        }
    }

    private fun showCamera() {
        CameraActivity.createIntent(this).let(this::startActivity)
    }
}
