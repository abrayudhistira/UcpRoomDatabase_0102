package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

class MataKuliahViewModel

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)