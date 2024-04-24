package com.curic4t.android.workout.view.main.ui.main.location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationSettingToDashBoardArguments(
    val postCode: String,
    val fullRoadAddr: String,
    val jibunAddr: String
) : Parcelable

