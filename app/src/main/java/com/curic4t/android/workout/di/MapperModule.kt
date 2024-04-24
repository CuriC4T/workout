package com.curic4t.android.workout.di

import com.curic4t.android.workout.data.mapper.ShortTermFcstMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideShortTermFcstMapper() = ShortTermFcstMapper()
}