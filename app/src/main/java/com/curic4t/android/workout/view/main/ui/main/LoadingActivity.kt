package com.curic4t.android.workout.view.main.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.curic4t.android.workout.databinding.ActivityLoadingBinding
import com.curic4t.android.workout.view.main.ui.base.BaseActivity
import com.curic4t.android.workout.view.main.ui.utils.PermissionUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class LoadingActivity : BaseActivity() {
    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        var count = 1

        for (entry in it.entries) {
            //entry.key : 권한
            Log.d("TESTTEST", "$count")
            if (count == it.size) {
                // 모든 권한을 가지면 메인 화면으로 이동
            } else {
                // 권한 확인 수가 모자르면 수락했는지 확인
                if (!entry.value) {
                    makeDialog("권한 없음")
                } else {
                    count++
                }
            }
        }

    }
    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)


        //requestPermission.launch(PermissionUtil.locationPermissions)
        val intent = Intent(this, MainActivity::class.java)
        val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        startActivity(intent, bundle)

    }

    private fun makeDialog(msg: String) {
        Log.d("TTT", this.toString())

        MaterialAlertDialogBuilder(this)
            .setMessage(msg)
            .setPositiveButton("확인") { dialog, which ->
                finish()
            }
            .show()

    }
}