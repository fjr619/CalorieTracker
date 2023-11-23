package com.fjr619.core.base.domain.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fjr619.core.base.domain.model.ActivityLevel
import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.base.domain.model.GoalType
import com.fjr619.core.base.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface IPreferences {
    suspend fun saveGender(gender: Gender)
    suspend fun saveAge(age: Int)
    suspend fun saveWeight(weight: Float)
    suspend fun saveHeight(height: Int)
    suspend fun saveActivityLevel(level: ActivityLevel)
    suspend fun saveGoalType(type: GoalType)
    suspend fun saveCarbRatio(ratio: Float)
    suspend fun saveProteinRatio(ratio: Float)
    suspend fun saveFatRatio(ratio: Float)

    fun loadUserInfo(): Flow<UserInfo>

    suspend fun saveShowOnboarding(show: Boolean)
    fun showOnboarding(): Flow<Boolean>

    companion object {
        const val USER_PREFERENCES = "user_preferences"
        val KEY_GENDER = stringPreferencesKey("gender")
        val KEY_AGE = intPreferencesKey("age")
        val KEY_WEIGHT = floatPreferencesKey("weight")
        val KEY_HEIGHT = intPreferencesKey("height")
        val KEY_ACTIVITY_LEVEL = stringPreferencesKey("activity_level")
        val KEY_GOAL_TYPE = stringPreferencesKey("goal_type")
        val KEY_CARB_RATIO = floatPreferencesKey("carb_ratio")
        val KEY_PROTEIN_RATIO = floatPreferencesKey("protein_ratio")
        val KEY_FAT_RATIO = floatPreferencesKey("fat_ratio")
        val KEY_SHOULD_SHOW_ONBOARDING = booleanPreferencesKey("should_show_onboarding")
    }
}