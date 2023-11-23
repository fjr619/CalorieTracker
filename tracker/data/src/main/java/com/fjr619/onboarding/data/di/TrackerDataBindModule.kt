package com.fjr619.onboarding.data.di

import com.fjr619.onboarding.data.repository.TrackerRepositoryImpl
import com.fjr619.tracker.domain.repository.TrackerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class TrackerDataBindModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTrackerRepository(trackerRepositoryImpl: TrackerRepositoryImpl): TrackerRepository
}