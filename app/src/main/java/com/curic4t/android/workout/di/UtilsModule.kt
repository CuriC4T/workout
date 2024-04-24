package com.curic4t.android.workout.di

import com.curic4t.android.workout.view.main.ui.utils.DateUtil
import com.curic4t.android.workout.view.main.ui.utils.LocationUtil
import com.curic4t.android.workout.view.main.ui.utils.PermissionUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    // 위치 관련 유틸
    @Provides
    @Singleton
    fun provideLocationUtil() = LocationUtil()

    // 권한 관련 유틸
    @Provides
    @Singleton
    fun provideRequestPermissionUtil() = PermissionUtil()

    // 날짜 관련 유틸
    @Provides
    @Singleton
    fun provideDateUtil() = DateUtil()
}