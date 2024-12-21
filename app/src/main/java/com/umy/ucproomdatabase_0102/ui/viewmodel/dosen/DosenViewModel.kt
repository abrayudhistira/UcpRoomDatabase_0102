package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.repository.RepoDosen

class DosenViewModel (private val repoDosen: RepoDosen) : ViewModel() {

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