package com.ahmaddudayef.moviescompose.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.usecase.GetMoviesUseCase
import com.ahmaddudayef.moviescompose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movieState: MutableStateFlow<UiState<List<Movie>>> =
        MutableStateFlow(UiState.Loading)
    val movieState: StateFlow<UiState<List<Movie>>> get() = _movieState


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

}