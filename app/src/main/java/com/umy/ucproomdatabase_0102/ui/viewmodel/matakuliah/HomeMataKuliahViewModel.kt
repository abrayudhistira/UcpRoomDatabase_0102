package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeMataKuliahViewModel (
    private val repoMataKuliah: RepoMataKuliah
) : ViewModel() {
    val homeMataKuliahUiState: StateFlow<HomeMataKuliahUiState> = repoMataKuliah.getAllMataKuliah()
        .filterNotNull()
        .map {
            HomeMataKuliahUiState (
                listMataKuliah = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeMataKuliahUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeMataKuliahUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMataKuliahUiState(
                isLoading = true,
            )
        )
}

data class HomeMataKuliahUiState(
    val listMataKuliah: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)