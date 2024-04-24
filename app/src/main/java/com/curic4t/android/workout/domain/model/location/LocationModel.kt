package com.curic4t.android.workout.domain.model.location

data class LocationModel(
    val latitude: Long,
    val longitude: Long,
    val postCode: String,
    val fullRoadAddr: String,
    val jibunAddr: String,
)