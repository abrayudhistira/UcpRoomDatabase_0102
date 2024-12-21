package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.repository.RepoDosen
import kotlinx.coroutines.launch

class DosenViewModel (private val repoDosen: RepoDosen) : ViewModel() {
    var uiState by mutableStateOf(DosenUIState())

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN TIDAK BOLEH KOSONG",
            nama = if (event.nama.isNotEmpty()) null else "NAMA TIDAK BOLEH KOSONG",
            jeniskelamiN = if (event.jeniskelamin.isNotEmpty()) null else "JENIS KELAMIN TIDAK BOLEH KOSONG"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoDosen.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid"
            )
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid:FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nidn:String? =null,
    val nama:String?= null,
    val jeniskelamiN:String?=null
){
    fun isValid():Boolean{
        return nidn == null && nama == null &&jeniskelamiN== null
    }
}

fun DosenEvent.toDosenEntity():Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jeniskelamin = jeniskelamin,
)

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
)