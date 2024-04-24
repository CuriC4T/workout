package com.curic4t.android.workout.di

import com.curic4t.android.workout.data.impl.ShortTermFcstRepoImpl
import com.curic4t.android.workout.data.mapper.ShortTermFcstMapper
import com.curic4t.android.workout.data.service.ShortTermFcstService
import com.curic4t.android.workout.domain.repo.ShortTermFcstRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShortTermFcstRepo(
        shortTermFcstRepoImpl: ShortTermFcstRepoImpl
    ): ShortTermFcstRepo
}