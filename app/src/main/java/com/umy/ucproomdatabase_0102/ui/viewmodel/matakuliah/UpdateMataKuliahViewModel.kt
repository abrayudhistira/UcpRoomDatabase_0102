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

    fun validateFields() : Boolean {
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorState(
            kd_mk = if (event.kd_mk.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama_mk = if (event.nama_mk.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semeseter tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosen_pengampu = if (event.dosen_pengampu.isNotEmpty()) null else "Dosen tidak boleh kosong",
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoMataKuliah.updateMataKuliah(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Berhasil Diupdate",
                        matakuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.
                    snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data Gagal Diupdate"
            )
        }
    }
}


fun resetSnackBarMessage() {
    updateUIState = updateUIState.copy(snackBarMessage = null)
}

fun MataKuliah.toUiStateMataKuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMataKuliahEvent(),
)