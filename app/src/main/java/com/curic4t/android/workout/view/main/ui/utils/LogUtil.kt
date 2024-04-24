package com.curic4t.android.workout.view.main.ui.utils

import android.util.Log
import com.curic4t.android.workout.BuildConfig

class LogUtil {
    companion object{
        fun d(tag: String, msg: String) {
            if (BuildConfig.BUILD_TYPE == "debug") {
                Log.d("DEBUG $tag", msg)
            }
        }

        fun e(tag: String, msg: String){
            if (BuildConfig.BUILD_TYPE == "debug") {
                Log.d("ERROR $tag", msg)
            }
        }
    }

}