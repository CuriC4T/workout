package com.curic4t.android.workout.di

import com.curic4t.android.workout.data.apiinterface.AirPollutionForecastApiInterface
import com.curic4t.android.workout.data.apiinterface.LivingWeatherIndexApiInterface
import com.curic4t.android.workout.data.apiinterface.ShortTermFcstApiInterface
import com.curic4t.android.workout.data.service.ShortTermFcstService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LivingWeatherIndexRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ShortTermForecastRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AirPollutionForecastRetrofit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    companion object {
        //기상청:Meteorological Administration
        //기상청_생활기상지수
        val MA_LivingWeatherIndex_Base_URL = "https://apis.data.go.kr/1360000/LivingWthrIdxServiceV4/"

        //기상청_단기예보
        val MA_ShortTermForecast_Base_URL =
            "https://apis.data.go.kr/"

        //한국환경공단:Korea Environment Corporation
        //한국환경공단 대기오염 예보정보
        val KEC_AirPollutionForecast_Base_URL = "api.odcloud.kr/api/"

    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor() = Interceptor {
        val request = it.request()
            .newBuilder()
            .build()
        return@Interceptor it.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(50000, TimeUnit.MILLISECONDS)
            .readTimeout(50000, TimeUnit.MILLISECONDS)
            .writeTimeout(50000, TimeUnit.MILLISECONDS)
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @LivingWeatherIndexRetrofit
    fun provideLivingWeatherIndexRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(MA_LivingWeatherIndex_Base_URL)
        .addConverterFactory(gsonConverterFactory).build()

    @Provides
    @Singleton
    @ShortTermForecastRetrofit
    fun provideShortTermForecastRetrofitRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(MA_ShortTermForecast_Base_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory).build()

    @Provides
    @Singleton
    @AirPollutionForecastRetrofit
    fun provideAirPollutionForecastRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(KEC_AirPollutionForecast_Base_URL)
        .addConverterFactory(gsonConverterFactory).build()

    @Provides
    @Singleton
    fun provideLivingWeatherIndexApiService(@LivingWeatherIndexRetrofit retrofit: Retrofit) =
        retrofit.create(LivingWeatherIndexApiInterface::class.java)

    @Provides
    @Singleton
    fun provideShortTermForecastApiService(@ShortTermForecastRetrofit retrofit: Retrofit) =
        retrofit.create(ShortTermFcstService::class.java)

    @Provides
    @Singleton
    fun provideAirPollutionForecastApiService(@AirPollutionForecastRetrofit retrofit: Retrofit) =
        retrofit.create(AirPollutionForecastApiInterface::class.java)
}