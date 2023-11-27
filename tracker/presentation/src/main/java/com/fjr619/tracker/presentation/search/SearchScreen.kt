package com.fjr619.tracker.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.fjr619.core.base.R
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.tracker.domain.model.TrackableFood
import com.fjr619.tracker.presentation.search.components.SearchTextField
import com.fjr619.tracker.presentation.search.components.TrackableFoodItem

@Composable
fun SearchScreen(
    mealName: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onItemClick: (TrackableFood) -> Unit,
    onAmountForFoodChange: (TrackableFood, String) -> Unit,
    onTrack: (TrackableFood) -> Unit,
    state: SearchUiState
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(text = state.query, onValueChange = onTextChange, onSearch = onSearch)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.trackableFood) { food ->
                TrackableFoodItem(
                    modifier = Modifier.fillMaxWidth(),
                    trackableFoodUiState = food,
                    onClick = {
                        onItemClick(food.food)
                    },
                    onAmountChange = {
                        onAmountForFoodChange(food.food, it)
                    },
                    onTrack = {
                        onTrack(food.food)
                    })
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFood.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}