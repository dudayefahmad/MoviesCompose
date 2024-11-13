package com.ahmaddudayef.moviescompose.ui.screens.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ahmaddudayef.moviescompose.R
import com.ahmaddudayef.moviescompose.data.Constants
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.ui.common.UiState
import com.ahmaddudayef.moviescompose.ui.components.ErrorScreen

@Composable
fun MovieScreen(
    modifier: Modifier = Modifier, viewModel: MovieViewModel = hiltViewModel()
) {
    val movieState by viewModel.movieState.collectAsStateWithLifecycle()
    when (movieState) {
        is UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                CircularProgressIndicator()
            }
            viewModel.fetchMovies()
        }

        is UiState.Success -> {
            MovieContent((movieState as UiState.Success<List<Movie>>).data)
        }

        is UiState.Error -> {
            ErrorScreen(
                errorMessage = (movieState as UiState.Error).errorMessage,
                onRetry = { viewModel.fetchMovies() },
                modifier = modifier
            )
        }
    }
}

@Composable
private fun MovieContent(
    listMovie: List<Movie>, modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag("MovieList")
        ) {
            itemsIndexed((listMovie)) { index, movie ->
                MovieItem(title = movie.title,
                    rating = movie.rating.toString(),
                    posterUrl = movie.posterUrl,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .animateEnterExit(enter = slideInVertically(animationSpec = spring(
                            stiffness = StiffnessVeryLow,
                            dampingRatio = DampingRatioLowBouncy
                        ), initialOffsetY = { it * (index + 1) } // staggered entrance
                        )))
            }
        }
    }
}

@Composable
fun MovieItem(
    title: String,
    rating: String,
    posterUrl: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = title, style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Rating: $rating",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = "${Constants.TMDB_IMAGE_URL}${posterUrl}",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}