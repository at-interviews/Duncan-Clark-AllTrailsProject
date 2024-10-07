package com.example.duncanclark.ui_feature_search_nearby_places.composable.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.ui_feature_search_nearby_places.composable.component.LazySearchResultsColumn

@Composable
fun SearchNearbyPlacesResults(
    modifier: Modifier,
    queryResult: List<Place>,
) {
    var showFab by remember { mutableStateOf(true) }
    var showList by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
                exit = fadeOut(animationSpec = tween(durationMillis = 2000))
            ) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = {
                        showList = !showList
                        showFab = false
                    }
                ) {
                    val fabText = when(showList) {
                        true -> "Map"
                        false -> "List"
                    }
                    Text(text = fabText)
                }
            }
        }
    ) { innerPadding ->
        if (showList) {
            LazySearchResultsColumn(
                modifier = modifier.padding(innerPadding),
                queryResult = queryResult
            )
        }
        MapContent(
            modifier = modifier.padding(innerPadding),
            queryResult = queryResult,
            showFabCallBack = {
            }
        )
    }
}