package com.example.cuteanimals.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuteanimals.data.model.Cat
import com.example.cuteanimals.data.repo.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val mainRepository: RemoteRepository): ViewModel() {

    private val _mainItem = MutableStateFlow<UIState<List<Cat>>>(UIState.Loading)
    val mainItem: StateFlow<UIState<List<Cat>>> = _mainItem


    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _mainItem.emit(UIState.Loading)
            mainRepository
                .getCatList()
                .flowOn(Dispatchers.IO)
                .catch {
                    _mainItem.emit(UIState.Failure(it))
                }
                .collect {
                    _mainItem.emit(UIState.Success(it))
                }
        }
    }
}