package com.fjr619.tracker.domain.use_case

import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class DeleteTrackedFood @Inject constructor(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}