package com.curic4t.android.workout.domain.repo

import com.curic4t.android.workout.domain.model.today.TodayFcstModel

interface ShortTermFcstRepo {
    suspend fun getTodayFcst(
        pageNo: String,
        numOfRows: String,
        currentDate: String,
        currentTime: String,
        nx: Int,
        ny: Int
    ): List<TodayFcstModel>
}