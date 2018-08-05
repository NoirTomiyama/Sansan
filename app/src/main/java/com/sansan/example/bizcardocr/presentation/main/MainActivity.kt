package com.sansan.example.bizcardocr.presentation.main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sansan.example.bizcardocr.R
import com.sansan.example.bizcardocr.presentation.camera.CameraActivity
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.RuntimePermissions
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import permissions.dispatcher.NeedsPermission
import android.widget.Toast
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnNeverAskAgain



@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener {
            // TODO: 課題1 (Runtime Permissionを実装してカメラ画面、そしてカメラ映像を表示できるようにしてください。)
            // 下記showCameraメソッドはそのまま呼び出すとカメラ画像は表示できるものの、Runtime Permissionが未実装のためカメラ映像を表示することができません。
            // そのためRuntime Permissionを実装し、許可ダイアログで許可を取ってからカメラ画面を表示するように修正してください。

            // showCamera()

            setShowCameraWithPermissionCheck()

        }
    }

    private fun showCamera() {
        CameraActivity.createIntent(this).let(this::startActivity)
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun setShowCamera() {
        showCamera()
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun onShowRationaleReadExternalStorage(request: PermissionRequest) {
        // すでに１度パーミッションのリクエストが行われていて、
        // ユーザーに「許可しない（二度と表示しないは非チェック）」をされていると
        // この処理が呼ばれます。
        Toast.makeText(this, "パーミッション許可がOFFになっています。", Toast.LENGTH_SHORT).show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onPermissionDeniedReadExternalStorage() {
        Toast.makeText(this, "リクエストが拒否されました。", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onNeverAskAgainReadExternalStorage() {
        Toast.makeText(this, "パーミッションが拒絶されています。", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == REQUEST_CODE_PERMISSION) {
            // requestPermissionsで設定した順番で結果が格納されています。
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 許可されたので処理を続行
                showCamera()

            } else {
                // パーミッションのリクエストに対して「許可しない」
                // または以前のリクエストで「二度と表示しない」にチェックを入れられた状態で
                // 「許可しない」を押されていると、必ずここに呼び出されます。

                Toast.makeText(this, "パーミッションが許可されていません。", Toast.LENGTH_SHORT).show()

            }
            return
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}
