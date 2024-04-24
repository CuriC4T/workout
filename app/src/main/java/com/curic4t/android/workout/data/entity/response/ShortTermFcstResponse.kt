package com.curic4t.android.workout.data.entity.response

import com.google.gson.annotations.SerializedName
//항목값	항목명	        단위	압축bit수
//POP	    강수확률	    %	        8
//PTY	    강수형태	    코드값	    4
//PCP	    1시간 강수량	범주 (1 mm)	    8
//REH	    습도	        %	        8
//SNO	    1시간 신적설	범주(1 cm)	    8
//SKY	    하늘상태	    코드값	    4
//TMP	    1시간 기온	    ℃	        10
//TMN	    일 최저기온	    ℃	        10
//TMX	    일 최고기온	    ℃	        10
//UUU	    풍속(동서성분)	m/s	        12
//VVV	    풍속(남북성분)	m/s	        12
//WAV	    파고	        M	        8
//VEC	    풍향	        deg	        10
//WSD	    풍속	        m/s	        10

//단기예보
data class ShortTermDataResponse(
    @SerializedName("response")
    val response: Response
)
data class Response(
    @SerializedName("header")
    val header: Header,
    @SerializedName("body")
    val body: Body
)

data class Header(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)

data class Body(
    @SerializedName("dataType")
    val dataType: String,
    @SerializedName("items")
    val items: Items,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class Items(
    @SerializedName("item")
    val item: Array<Item>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Items

        return item.contentEquals(other.item)
    }

    override fun hashCode(): Int {
        return item.contentHashCode()
    }
}
data class Item(
    @SerializedName("baseDate")
    val baseDate: String,
    @SerializedName("baseTime")
    val baseTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("fcstDate")
    val fcstDate: String,
    @SerializedName("fcstTime")
    val fcstTime: String,
    @SerializedName("fcstValue")
    val fcstValue: String,
    @SerializedName("nx")
    val nx: Float,
    @SerializedName("ny")
    val ny: Float
)