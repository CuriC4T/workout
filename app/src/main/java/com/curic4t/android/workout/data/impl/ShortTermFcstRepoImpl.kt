package com.curic4t.android.workout.data.impl

import com.curic4t.android.workout.BuildConfig
import com.curic4t.android.workout.data.entity.response.ShortTermDataResponse
import com.curic4t.android.workout.data.mapper.ShortTermFcstMapper
import com.curic4t.android.workout.data.service.ShortTermFcstService
import com.curic4t.android.workout.domain.model.today.TodayFcstModel
import com.curic4t.android.workout.domain.repo.ShortTermFcstRepo
import com.curic4t.android.workout.view.main.ui.utils.LogUtil
import javax.inject.Inject

class ShortTermFcstRepoImpl @Inject constructor(
    private val shortTermFcstService: ShortTermFcstService,
    private val shortTermFcstMapper: ShortTermFcstMapper
) : ShortTermFcstRepo {
    override suspend fun getTodayFcst(
        pageNo: String,
        numOfRows: String,
        currentDate: String,
        currentTime: String,
        nx: Int,
        ny: Int
    ): List<TodayFcstModel> {
        return try {
            val response: ShortTermDataResponse =
                shortTermFcstService.getShortTermFcst(
                    serviceKey = BuildConfig.WEATHER_API_KEY,
                    pageNo = pageNo,
                    numOfRows = numOfRows,
                    base_date = currentDate,
                    base_time = currentTime,
                    nx = nx,
                    ny = ny
                )
            shortTermFcstMapper.toTodayFcstModel(response)
        } catch (e: Exception) {
            LogUtil.e("ShortTermFcstRepoImpl", "ERROR: ${e.stackTrace}\n ${e.message}\n ${e.cause}")
            return listOf()
        }

    }
}