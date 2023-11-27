package com.fjr619.tracker.domain.use_case

import android.util.Log
import com.fjr619.tracker.domain.model.MealType
import com.fjr619.tracker.domain.model.TrackableFood
import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt
class TrackFood @Inject constructor(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        val a =             TrackedFood(
            name = food.name,
            carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
            protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
            fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
            calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
            imageUrl = food.imageUrl,
            mealType = mealType,
            amount = amount,
            date = date,
        )

        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date,
            )
        )
    }
}