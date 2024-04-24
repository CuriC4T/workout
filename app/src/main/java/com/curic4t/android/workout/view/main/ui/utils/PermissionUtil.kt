package com.curic4t.android.workout.view.main.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Singleton

@Singleton
class PermissionUtil {

    companion object{
        val REQUEST_LOCATION = 1

        val essentialPermissions = mutableSetOf<String>(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
            //Manifest.permission.ACCESS_BACKGROUND_LOCATION api 29이상 백그라운드 필요
        )
    }


//
//    fun requestPermission(activity: Activity, permissions: Array<String>) {
//        permissions.forEach {
//            if (ActivityCompat.checkSelfPermission(
//                    activity,
//                    it
//                ) == PackageManager.PERMISSION_DENIED
//            ) {
//                ActivityCompat.requestPermissions(
//                    activity,
//                    permissions,
//                    REQUEST_LOCATION
//                )
//            }
//        }
//    }

    fun checkEssentialPermission(context: Context, permissons: Array<String>): Boolean {
        permissons.forEach {

            if (ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_DENIED
            ) {
                return false
            }
        }
        return true
    }


}