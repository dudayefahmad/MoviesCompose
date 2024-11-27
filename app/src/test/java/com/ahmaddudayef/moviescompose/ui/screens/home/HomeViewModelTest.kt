package com.ahmaddudayef.moviescompose.ui.screens.home

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.model.TvShow
import com.ahmaddudayef.moviescompose.domain.usecase.GetMoviesUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetTvShowsUseCase
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
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var getTvShowsUseCase: GetTvShowsUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = mockk()
        getTvShowsUseCase = mockk()
        viewModel = HomeViewModel(getMoviesUseCase, getTvShowsUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain() // Reset Main dispatcher after testing
    }

    @Test
    fun `fetchMovies should update movieState to Success when use case returns success`() =
        runTest {
            // Given
            val expectedMovies = listOf(Movie("1", "Movie 1", "posterUrl", 7.5f))
            coEvery { getMoviesUseCase() } returns flowOf(Result.Success(expectedMovies))

            // When
            viewModel.fetchMovies()
            advanceUntilIdle()

            // Then
            // Assertion adjusted to match UiState.Success structure
            Assert.assertEquals(UiState.Success(expectedMovies), viewModel.movieState.value)
        }

    @Test
    fun `fetchMovies should update movieState to Error when use case returns error`() = runTest {
        // Given
        val expectedErrorMessage = "Network error"
        coEvery { getMoviesUseCase() } returns flowOf(Result.Error(Exception(expectedErrorMessage)))

        // When
        viewModel.fetchMovies()
        advanceUntilIdle()

        // Then
        // Assertion adjusted to match UiState.Error structure
        Assert.assertEquals(UiState.Error(expectedErrorMessage), viewModel.movieState.value)
    }

    @Test
    fun `fetchTvShows should update tvShowState to Success when use case returns success`() =
        runTest {
            // Given
            val expectedTvShows = listOf(TvShow("1", "Tv Show 1", "posterUrl", 8.2f))
            coEvery { getTvShowsUseCase() } returns flowOf(Result.Success(expectedTvShows))

            // When
            viewModel.fetchTvShows()
            advanceUntilIdle()


            // Then
            // Assertion adjusted to match UiState.Success structure
            Assert.assertEquals(UiState.Success(expectedTvShows), viewModel.tvShowState.value)
        }

    @Test
    fun `fetchTvShows should update tvShowState to Error when use case returns error`() = runTest {
        // Given
        val expectedErrorMessage = "Network error"
        coEvery { getTvShowsUseCase() } returns flowOf(Result.Error(Exception(expectedErrorMessage)))

        // When
        viewModel.fetchTvShows()
        advanceUntilIdle()

        // Then
        // Assertion adjusted to match UiState.Error structure
        Assert.assertEquals(UiState.Error(expectedErrorMessage), viewModel.tvShowState.value)
    }
}