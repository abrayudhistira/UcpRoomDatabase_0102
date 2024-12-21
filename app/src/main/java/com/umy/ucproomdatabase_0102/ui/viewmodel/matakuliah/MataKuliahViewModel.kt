package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

class MataKuliahViewModel

data class MataKuliahEvent(
    val kd_mk: String = "",
    val nama_mk: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosen_pengampu: String = ""
)