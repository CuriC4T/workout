package com.curic4t.android.workout.data.entity.request

import com.google.gson.annotations.SerializedName

data class ShortTermFcstRequest(
    @SerializedName("ServiceKey")
    val serviceKey: String,
    @SerializedName("pageNo")
    val pageNo: String,
    @SerializedName("numOfRows")
    val numOfRows: String,
    @SerializedName("dataType")
    val dataType: String = "JSON",
    @SerializedName("base_date")
    val base_date: String,
    @SerializedName("base_time")
    val base_time: String,
    @SerializedName("nx")
    val nx: Float,
    @SerializedName("ny")
    val ny: Float
)
