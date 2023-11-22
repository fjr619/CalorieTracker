package com.fjr619.calorietracker.di

import com.fjr619.onboarding.domain.usecase.OnboardingUseCase
import com.fjr619.onboarding.domain.usecase.SaveGender
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideOnboardingUseCase(
        saveGender: SaveGender
    ): OnboardingUseCase = OnboardingUseCase(
        saveGender
    )
}