package com.curic4t.android.workout.view.main.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.curic4t.android.workout.view.main.ui.utils.LogUtil
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationLiveData(private val context: Context) : LiveData<Pair<Double, Double>>() {

    override fun onInactive() {
        super.onInactive()
    }

    override fun onActive() {
        super.onActive()
        updateLocation()
    }

    @SuppressLint("MissingPermission")
    fun updateLocation() {
        LogUtil.d("LocationUtil", "${value?.first} ${value?.second}")
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    LogUtil.d("LocationUtil", "${it.latitude} ${it.longitude}")
                    value = Pair(it.latitude, it.longitude)
                }
            }.addOnFailureListener {
                LogUtil.d("LocationUtil", "$it")
            }
    }
}