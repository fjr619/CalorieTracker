package com.fjr619.tracker.domain.use_case

import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodsForDate @Inject constructor(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}