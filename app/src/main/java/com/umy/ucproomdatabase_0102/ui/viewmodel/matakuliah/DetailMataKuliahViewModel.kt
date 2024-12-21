package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah
import com.umy.ucproomdatabase_0102.ui.navigation.DestinasiMataKuliahDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoMataKuliah: RepoMataKuliah,
) : ViewModel() {
    private val _KdMk: String = checkNotNull(savedStateHandle[DestinasiMataKuliahDetail.KODE]) // Kosongkan dulu parameternya

    val detailMataKuliahUiState: StateFlow<DetailMataKuliahUiState> = repoMataKuliah.getDetail(_KdMk)
        .filterNotNull()
        .map {
            DetailMataKuliahUiState(
                detailMatakuliahUiEvent = it.toDetailMataKuliahEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMataKuliahUiState(isLoading = true))
            delay(5000)
        }
        .catch {
            emit(
                DetailMataKuliahUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailMataKuliahUiState(
                isLoading = true,
            ),
        )

    fun deleteMataKuliah() {
        detailMataKuliahUiState.value.detailMatakuliahUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repoMataKuliah.deleteMataKuliah(it)
            }
        }
    }
}

data class DetailMataKuliahUiState(
    val detailMatakuliahUiEvent: MataKuliahEvent = MataKuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailMatakuliahUiEvent == MataKuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailMatakuliahUiEvent != MataKuliahEvent()
}

fun MataKuliah.toDetailMataKuliahEvent(): MataKuliahEvent {
    return MataKuliahEvent(
        kd_mk = kd_mk,
        nama_mk = nama_mk,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosen_pengampu = dosen_pengampu
    )
}