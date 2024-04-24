package com.curic4t.android.workout.di

import com.curic4t.android.workout.domain.repo.ShortTermFcstRepo
import com.curic4t.android.workout.domain.usecase.GetShortTermFcstUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideShortTermFastUseCase(shortTermFcstRepo: ShortTermFcstRepo) = GetShortTermFcstUseCase(shortTermFcstRepo)
}