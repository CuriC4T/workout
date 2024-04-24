package com.curic4t.android.workout.domain.usecase

import com.curic4t.android.workout.domain.model.today.TodayFcstModel
import com.curic4t.android.workout.domain.repo.ShortTermFcstRepo
import javax.inject.Inject

class GetShortTermFcstUseCase @Inject constructor(
    private val shortTermFcstRepo: ShortTermFcstRepo
) {

    suspend operator fun invoke(
        pageNo: String,
        numOfRows: String,
        currentDate: String,
        currentTime: String,
        nx: Int,
        ny: Int
    ): List<TodayFcstModel> =
        shortTermFcstRepo.getTodayFcst(
            pageNo,
            numOfRows,
            currentDate,
            currentTime,
            nx,
            ny
        )
}