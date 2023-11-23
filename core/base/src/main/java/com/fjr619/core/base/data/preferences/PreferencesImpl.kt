package com.fjr619.core.base.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.fjr619.core.base.domain.model.ActivityLevel
import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.base.domain.model.GoalType
import com.fjr619.core.base.domain.model.UserInfo
import com.fjr619.core.base.domain.preferences.IPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesImpl(
    private val dataStore: DataStore<Preferences>
): IPreferences {
    override suspend fun saveGender(gender: Gender) {
        dataStore.edit {
            it[IPreferences.KEY_GENDER] = gender.name
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.edit {
            it[IPreferences.KEY_AGE] = age
        }
    }

    override suspend fun saveWeight(weight: Float) {
        dataStore.edit {
            it[IPreferences.KEY_WEIGHT] = weight
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.edit {
            it[IPreferences.KEY_HEIGHT] = height
        }
    }

    override suspend fun saveActivityLevel(level: ActivityLevel) {
        dataStore.edit {
            it[IPreferences.KEY_ACTIVITY_LEVEL] = level.name
        }
    }

    override suspend fun saveGoalType(type: GoalType) {
        dataStore.edit {
            it[IPreferences.KEY_GOAL_TYPE] = type.name
        }
    }

    override suspend fun saveCarbRatio(ratio: Float) {
        dataStore.edit {
            it[IPreferences.KEY_CARB_RATIO] = ratio
        }
    }

    override suspend fun saveProteinRatio(ratio: Float) {
        dataStore.edit {
            it[IPreferences.KEY_PROTEIN_RATIO] = ratio
        }
    }

    override suspend fun saveFatRatio(ratio: Float) {
        dataStore.edit {
            it[IPreferences.KEY_FAT_RATIO] = ratio
        }
    }

    override fun loadUserInfo(): Flow<UserInfo> {
        return dataStore.data.map {
            UserInfo(
                gender = Gender.fromString(it[IPreferences.KEY_GENDER] ?: "male"),
                age = it[IPreferences.KEY_AGE] ?: -1,
                height = it[IPreferences.KEY_HEIGHT] ?: -1,
                weight = it[IPreferences.KEY_WEIGHT] ?: -1f,
                activityLevel = ActivityLevel.fromString(it[IPreferences.KEY_ACTIVITY_LEVEL] ?: "medium"),
                goalType = GoalType.fromString(it[IPreferences.KEY_GOAL_TYPE] ?: "keep_weight"),
                carbRatio = it[IPreferences.KEY_CARB_RATIO] ?: -1f,
                proteinRatio = it[IPreferences.KEY_PROTEIN_RATIO] ?: -1f,
                fatRatio = it[IPreferences.KEY_FAT_RATIO] ?: -1f
            )
        }
    }

    override suspend fun saveShowOnboarding(show: Boolean) {
        dataStore.edit {
            it[IPreferences.KEY_SHOULD_SHOW_ONBOARDING] = show
        }
    }

    override fun showOnboarding(): Flow<Boolean> = dataStore.data.map {
        it[IPreferences.KEY_SHOULD_SHOW_ONBOARDING] ?: true
    }
}