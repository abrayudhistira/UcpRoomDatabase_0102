package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.repository.RepoDosen

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