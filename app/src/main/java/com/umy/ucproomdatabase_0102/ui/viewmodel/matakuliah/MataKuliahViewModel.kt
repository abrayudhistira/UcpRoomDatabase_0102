package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah

class MataKuliahViewModel(private val repoMataKuliah: RepoMataKuliah
) : ViewModel() {
    var uiState by mutableStateOf(MatakuliahUIState())

    fun updateState(matakuliahEvent: MataKuliahEvent) {
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    fun validateFields() : Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorState(
            kd_mk = if (event.kd_mk.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama_mk = if (event.nama_mk.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosen_pengampu = if (event.dosen_pengampu.isNotEmpty()) null else "Dosen tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
}

data class MatakuliahUIState(
    val matakuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid:FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val kd_mk: String? = null,
    val nama_mk: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosen_pengampu: String? = null,
){
    fun isValid(): Boolean {
        return kd_mk == null && nama_mk == null && sks == null &&
                semester == null && jenis == null && dosen_pengampu == null
    }
}

fun MataKuliahEvent.toMatakuliahEntity(): MataKuliah = MataKuliah(
    kd_mk = kd_mk,
    nama_mk = nama_mk,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosen_pengampu = dosen_pengampu
)

data class MataKuliahEvent(
    val kd_mk: String = "",
    val nama_mk: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosen_pengampu: String = ""
)