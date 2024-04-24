package com.curic4t.android.workout.data.service

import com.curic4t.android.workout.data.entity.request.ShortTermFcstRequest
import com.curic4t.android.workout.data.entity.response.ShortTermDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface ShortTermFcstService {
    //https://apis.data.go.kr/1360000/
    // VilageFcstInfoService_2.0/
    // getVilageFcst
    // ?serviceKey=Oe2eUSXxdo2u2M1DYk7gBvyZ6Kq3PEwtHpSq17ZhPY0F8p%2FcavEl%2B1imSCINJL640sWfzxFJ8458XWtpiDY1Zw%3D%3D
    // &pageNo=1
    // &numOfRows=1000
    // &dataType=XML
    // &base_date=20210628
    // &base_time=0500
    // &nx=55
    // &ny=127
    @GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
    //suspend fun getShortTermFcst(shortTermFcstRequest: ShortTermFcstRequest): Response<ShortTermDataResponse>?
    suspend fun getShortTermFcst(
        @Query("serviceKey") serviceKey: String,
        @Query("pageNo") pageNo: String,
        @Query("numOfRows") numOfRows: String,
        @Query("dataType") dataType: String = "JSON",
        @Query("base_date") base_date: String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ): ShortTermDataResponse

}