package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoMataKuliah: RepoMataKuliah
) : ViewModel() {

    var updateUIState by mutableStateOf(MatakuliahUIState())
        private set

    private val _KdMk: String = checkNotNull(savedStateHandle[]) //kosongkan dahulu

    init {
        viewModelScope.launch {
            updateUIState = repoMataKuliah.getDetail(_KdMk)
                .filterNotNull()
                .first()
                .toUiStateMataKuliah()
        }
    }

    fun updateState (mataKuliahEvent: MataKuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = mataKuliahEvent
        )
    }

}


fun resetSnackBarMessage() {
    updateUIState = updateUIState.copy(snackBarMessage = null)
}

fun MataKuliah.toUiStateMataKuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMataKuliahEvent(),
)