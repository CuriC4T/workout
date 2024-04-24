package com.curic4t.android.workout.view.main.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.curic4t.android.workout.domain.model.location.LocationModel
import com.curic4t.android.workout.domain.model.today.TodayFcstModel
import com.curic4t.android.workout.domain.usecase.GetShortTermFcstUseCase
import com.curic4t.android.workout.view.main.ui.base.BaseAndroidViewModel
import com.curic4t.android.workout.view.main.ui.common.LocationLiveData
import com.curic4t.android.workout.view.main.ui.utils.DateUtil
import com.curic4t.android.workout.view.main.ui.utils.LocationUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAndroidViewModel @Inject constructor(
    private val getShortTermFast: GetShortTermFcstUseCase,
    private val locationUtil: LocationUtil, application: Application
) : BaseAndroidViewModel(application) {

    @Inject
    lateinit var dateUtil: DateUtil

    private val _todayLiveData = MutableLiveData<List<TodayFcstModel>>()
    val _testLiveData = MutableLiveData<LocationModel>()

    val todayLiveData: LiveData<List<TodayFcstModel>> get() = _todayLiveData
    val locationLiveData = LocationLiveData(application)


    suspend fun getTodayData() {
        val currentDate = dateUtil.getCurrentDate(true)
        var currentTime = dateUtil.getCurrentTime()
        if (locationLiveData.value?.first!=0.0 && locationLiveData.value?.second!=0.0){

        }else{

        }
        val response = getShortTermFast(
            pageNo = "1",
            numOfRows = "2000",
            currentDate = currentDate,
            currentTime = "0200",
            nx = 55,
            ny = 127
        )
        _todayLiveData.postValue(response)

    }

//    fun updateCurrentLocation() {
//        val pair = locationUtil.getLocation(context = getApplication())
//        _locationLiveData.postValue(pair)
//    }

    fun latlngToGrid(pair: Pair<Double, Double>): Pair<Int, Int> {
        return locationUtil.dfsXyConv(pair.first, pair.second)
    }


}