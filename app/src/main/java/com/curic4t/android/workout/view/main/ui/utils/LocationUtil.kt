package com.curic4t.android.workout.view.main.ui.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Point
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import javax.inject.Singleton


@Singleton
class LocationUtil {

    @SuppressLint("MissingPermission")
    fun getLocation(context: Application): Pair<Double, Double> {
        var longitude = 0.0
        var latitude = 0.0
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                LogUtil.d("LocationUtil", "TEST $location")
                location?.let {
                    LogUtil.d("LocationUtil", "${it.latitude} ${it.longitude}")
                    longitude = it.longitude
                    latitude = it.latitude

                }
            }.addOnFailureListener {
                LogUtil.d("LocationUtil", "$it")
            }

        return Pair(longitude, latitude)
    }

    // 위경도를 기상청에서 사용하는 격자 좌표로 변환
    fun dfsXyConv(v1: Double, v2: Double): Pair<Int, Int> {
        LogUtil.d("dfsXyConv","${v1} ${v2}")
        val RE = 6371.00877     // 지구 반경(km)
        val GRID = 5.0          // 격자 간격(km)
        val SLAT1 = 30.0        // 투영 위도1(degree)
        val SLAT2 = 60.0        // 투영 위도2(degree)
        val OLON = 126.0        // 기준점 경도(degree)
        val OLAT = 38.0         // 기준점 위도(degree)
        val XO = 43             // 기준점 X좌표(GRID)
        val YO = 136            // 기준점 Y좌표(GRID)
        val DEGRAD = Math.PI / 180.0
        val re = RE / GRID
        val slat1 = SLAT1 * DEGRAD
        val slat2 = SLAT2 * DEGRAD
        val olon = OLON * DEGRAD
        val olat = OLAT * DEGRAD

        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / Math.pow(ro, sn)

        var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5)
        ra = re * sf / Math.pow(ra, sn)
        var theta = v2 * DEGRAD - olon
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        val x = (ra * Math.sin(theta) + XO + 0.5).toInt()
        val y = (ro - ra * Math.cos(theta) + YO + 0.5).toInt()

        return Pair(x, y)
    }
}