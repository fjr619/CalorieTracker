package com.fjr619.onboarding.domain.usecase

import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.base.domain.preferences.IPreferences
import javax.inject.Inject

class SaveGender @Inject constructor(
    private val preferences: IPreferences
) {
    suspend operator fun invoke(gender: Gender) {
        preferences.saveGender(gender)
    }
}