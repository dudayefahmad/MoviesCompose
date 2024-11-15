package com.ahmaddudayef.moviescompose.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ahmaddudayef.moviescompose.R
import com.ahmaddudayef.moviescompose.data.Constants
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.ui.common.UiState
import com.ahmaddudayef.moviescompose.ui.components.ErrorScreen
import com.ahmaddudayef.moviescompose.ui.theme.MoviesComposeTheme

@Composable
fun DetailMovieScreen(
    movieId: Long,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val detailMovieState by viewModel.detailMovieState.collectAsStateWithLifecycle()
    when (detailMovieState) {
        is UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                CircularProgressIndicator()
            }
            viewModel.fetchDetailMovie(movieId)
        }

        is UiState.Success -> {
            val data = (detailMovieState as UiState.Success<DetailMovie>).data
            DetailMovieContent(
                data.posterUrl,
                data.title,
                data.overview,
                data.releaseDate,
                data.rating.toString(),
                onBackClick = navigateBack,
            )
        }

        is UiState.Error -> {
            ErrorScreen(
                errorMessage = (detailMovieState as UiState.Error).errorMessage,
                onRetry = { viewModel.fetchDetailMovie(movieId) },
                modifier = modifier
            )
        }
    }
}

@Composable
fun DetailMovieContent(
    posterUrl: String,
    title: String,
    overview: String,
    releaseDate: String,
    rating: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = "${Constants.TMDB_IMAGE_URL}${posterUrl}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
                }
                Text(
                    text = "Rating: $rating",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = "Overview: \n${overview}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Release Data: $releaseDate",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(LightGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    MoviesComposeTheme() {
        DetailMovieContent(
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "Tom Clancy's Without Remorse",
            "An elite Navy SEAL uncovers an international conspiracy they didn't initially expect ",
            "2023-04-29",
            "7.6",
            {}
        )
    }
}