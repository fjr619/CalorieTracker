package com.fjr619.tracker.domain.di

import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.domain.use_case.CalculateMealNutrients
import com.fjr619.tracker.domain.use_case.DeleteTrackedFood
import com.fjr619.tracker.domain.use_case.GetFoodsForDate
import com.fjr619.tracker.domain.use_case.SearchFood
import com.fjr619.tracker.domain.use_case.TrackFood
import com.fjr619.tracker.domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        trackFood: TrackFood,
        searchFood: SearchFood,
        getFoodsForDate: GetFoodsForDate,
        deleteTrackedFood: DeleteTrackedFood,
        calculateMealNutrients: CalculateMealNutrients
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = trackFood,
            searchFood = searchFood,
            getFoodsForDate = getFoodsForDate,
            deleteTrackedFood = deleteTrackedFood,
            calculateMealNutrients = calculateMealNutrients
        )
    }
}