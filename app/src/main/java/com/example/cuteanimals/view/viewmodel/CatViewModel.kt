package com.example.cuteanimals.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cuteanimals.data.model.Cat
import com.example.cuteanimals.data.repo.RemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import androidx.paging.map
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val mainRepository: RemoteRepositoryImpl): ViewModel() {

    private val _mainItem = MutableStateFlow<UIState<PagingData<Cat>>>(UIState.Loading)
    val mainItem: StateFlow<UIState<PagingData<Cat>>> = _mainItem


    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _mainItem.emit(UIState.Loading)
            getMovies()
                .flowOn(Dispatchers.IO)
                .catch {
                    _mainItem.emit(UIState.Failure(it))
                }
                .collect {
                    _mainItem.emit(UIState.Success(it))
                }
        }
    }

    private fun getMovies(): Flow<PagingData<Cat>> {
        return mainRepository.getCats()
            .map { pagingData ->
                pagingData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }
}