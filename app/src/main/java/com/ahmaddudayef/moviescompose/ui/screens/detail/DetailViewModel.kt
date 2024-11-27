package com.ahmaddudayef.moviescompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.domain.model.DetailTvShow
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailMovieUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailTvShowUseCase
import com.ahmaddudayef.moviescompose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getDetailTvShowUseCase: GetDetailTvShowUseCase,
) : ViewModel() {

    private val _detailMovieState: MutableStateFlow<UiState<DetailMovie>> =
        MutableStateFlow(UiState.Loading)
    val detailMovieState: StateFlow<
            UiState<DetailMovie>> get() = _detailMovieState

    private val _detailTvShowState: MutableStateFlow<UiState<DetailTvShow>> =
        MutableStateFlow(UiState.Loading)
    val detailTvShowState: StateFlow<UiState<DetailTvShow>> get() = _detailTvShowState

    fun fetchDetailMovie(id: Long) {
        viewModelScope.launch {
            _detailMovieState.value = UiState.Loading
            getDetailMovieUseCase(id).collect { result ->
                _detailMovieState.value = when (result) {
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception.message.toString())
                }
            }
        }
    }

    fun fetchDetailTvShow(id: Long) {
        viewModelScope.launch {
            _detailTvShowState.value = UiState.Loading
            getDetailTvShowUseCase(id).collect { result ->
                _detailTvShowState.value = when (result) {
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception.message.toString())
                }
            }
        }
    }
}