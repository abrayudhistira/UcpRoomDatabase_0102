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