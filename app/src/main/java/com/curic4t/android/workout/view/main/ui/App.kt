package com.curic4t.android.workout.view.main.ui

import android.app.Application
import com.curic4t.android.workout.BuildConfig
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {
    private var instance: App? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        KakaoMapSdk.init(this,BuildConfig.KAKAO_NATIVE_KEY)
    }
}