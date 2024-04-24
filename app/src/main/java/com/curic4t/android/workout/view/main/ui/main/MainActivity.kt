package com.curic4t.android.workout.view.main.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.NavHostFragment
import com.curic4t.android.workout.R
import com.curic4t.android.workout.databinding.ActivityMainBinding
import com.curic4t.android.workout.view.main.ui.base.BaseActivity
import com.curic4t.android.workout.view.main.ui.utils.PermissionUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class MainActivity : BaseActivity() {
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

    @Inject
    lateinit var permissionUtil: PermissionUtil
    var savedInstanceState: Bundle? = null
    lateinit var splashScreen: SplashScreen
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState

        splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        val hasPermission =
//            permissionUtil.checkEssentialPermission(this, PermissionUtil.locationPermissions)

    }

    private fun init() {


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
    }

    private fun makeDialog(msg: String) {

        MaterialAlertDialogBuilder(this)
            .setMessage(msg)
            .setPositiveButton("확인") { dialog, which ->
                finish()
            }.setOnCancelListener {
                finish()
            }
            .setOnDismissListener {
                finish()
            }
            .show()

    }


}