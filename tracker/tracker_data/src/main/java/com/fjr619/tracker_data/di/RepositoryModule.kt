package com.fjr619.tracker_data.di

import com.fjr619.tracker_data.local.TrackerDatabase
import com.fjr619.tracker_data.remote.OpenFoodApi
import com.fjr619.tracker_data.repository.TrackerRepositoryImpl
import com.fjr619.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }
}