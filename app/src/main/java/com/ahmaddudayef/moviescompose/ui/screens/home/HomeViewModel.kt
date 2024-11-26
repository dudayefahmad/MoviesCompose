package com.ahmaddudayef.moviescompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.model.TvShow
import com.ahmaddudayef.moviescompose.domain.usecase.GetMoviesUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetTvShowsUseCase
import com.ahmaddudayef.moviescompose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase,
) : ViewModel() {

    private val _movieState: MutableStateFlow<UiState<List<Movie>>> =
        MutableStateFlow(UiState.Loading)
    val movieState: StateFlow<UiState<List<Movie>>> get() = _movieState

    private val _tvShowState: MutableStateFlow<UiState<List<TvShow>>> =
        MutableStateFlow(UiState.Loading)
    val tvShowState: StateFlow<UiState<List<TvShow>>> get() = _tvShowState


    fun fetchMovies() {
        viewModelScope.launch {
            _movieState.value = UiState.Loading
            getMoviesUseCase().collect { result ->
                _movieState.value = when (result) {
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception.message.toString())
                }
            }
        }
    }

    fun fetchTvShows() {
        viewModelScope.launch {
            _tvShowState.value = UiState.Loading
            getTvShowsUseCase().collect { result ->
                _tvShowState.value = when (result) {
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception.message.toString())
                }
            }
        }
    }

}