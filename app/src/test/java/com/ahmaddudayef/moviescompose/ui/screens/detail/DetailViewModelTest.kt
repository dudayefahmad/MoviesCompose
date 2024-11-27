package com.ahmaddudayef.moviescompose.ui.screens.detail

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.domain.model.DetailTvShow
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailMovieUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailTvShowUseCase
import com.ahmaddudayef.moviescompose.ui.common.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var getDetailMovieUseCase: GetDetailMovieUseCase
    private lateinit var getDetailTvShowUseCase: GetDetailTvShowUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getDetailMovieUseCase = mockk()
        getDetailTvShowUseCase = mockk()
        viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchDetailMovie should update detailMovieState to Success when use case returns success`() =
        runTest {
            // Given
            val movieId = 1L
            val expectedMovieDetail =
                DetailMovie(
                    "1",
                    "Movie Title",
                    "Overview",
                    "posterPath",
                    "2023-10-26",
                    7.5f,

                    )
            coEvery { getDetailMovieUseCase(movieId) } returns flowOf(
                Result.Success(
                    expectedMovieDetail
                )
            )

            // When
            viewModel.fetchDetailMovie(movieId)
            advanceUntilIdle()

            // Then
            Assert.assertEquals(
                UiState.Success(expectedMovieDetail),
                viewModel.detailMovieState.value
            )
        }

    @Test
    fun `fetchDetailMovie should update detailMovieState to Error when use case returns error`() =
        runTest {
            // Given
            val movieId = 1L
            val expectedErrorMessage = "Network error"
            coEvery { getDetailMovieUseCase(movieId) } returns flowOf(
                Result.Error(
                    Exception(expectedErrorMessage)
                )
            )

            // When
            viewModel.fetchDetailMovie(movieId)
            advanceUntilIdle()

            // Then
            Assert.assertEquals(
                UiState.Error(expectedErrorMessage),
                viewModel.detailMovieState.value
            )
        }

    @Test
    fun `fetchDetailTvShow should update detailTvShowState to Success when use case returns success`() =
        runTest {
            // Given
            val tvShowId = 1L
            val expectedTvShowDetail =
                DetailTvShow(
                    "1",
                    "Tv Show Title",
                    "Overview",
                    "posterPath",
                    "2023-10-26",
                    8.2f
                )
            coEvery { getDetailTvShowUseCase(tvShowId) } returns flowOf(
                Result.Success(
                    expectedTvShowDetail
                )
            )

            // When
            viewModel.fetchDetailTvShow(tvShowId)
            advanceUntilIdle()

            // Then
            Assert.assertEquals(
                UiState.Success(expectedTvShowDetail),
                viewModel.detailTvShowState.value
            )
        }

    @Test
    fun `fetchDetailTvShow should update detailTvShowState to Error when use case returns error`() =
        runTest {
            // Given
            val tvShowId = 1L
            val expectedErrorMessage = "Network error"
            coEvery { getDetailTvShowUseCase(tvShowId) } returns flowOf(
                Result.Error(
                    Exception(expectedErrorMessage)
                )
            )

            // When
            viewModel.fetchDetailTvShow(tvShowId)
            advanceUntilIdle()

            // Then
            Assert.assertEquals(
                UiState.Error(expectedErrorMessage),
                viewModel.detailTvShowState.value
            )
        }

}