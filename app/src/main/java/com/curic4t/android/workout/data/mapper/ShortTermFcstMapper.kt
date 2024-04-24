package com.curic4t.android.workout.data.mapper

import com.curic4t.android.workout.data.entity.response.ShortTermDataResponse
import com.curic4t.android.workout.domain.model.today.TodayFcstModel
import com.curic4t.android.workout.view.main.ui.utils.LogUtil
import javax.inject.Singleton


@Singleton
class ShortTermFcstMapper {
    fun toTodayFcstModel(shortTermDataResponse: ShortTermDataResponse): List<TodayFcstModel> {

        val header = shortTermDataResponse.response.header
        LogUtil.d("ShortTermFcstMapper","resultCode: ${header.resultCode}")

        if (header.resultCode != "00" && header.resultCode != "0") return listOf()
        val body = shortTermDataResponse.response.body
        val items = body.items

        val result = ArrayList<TodayFcstModel>()
        items.item.forEach {
            val todayFcstModel = TodayFcstModel(
                it.fcstValue
            )
            result.add(todayFcstModel)
        }

        return result
    }
}