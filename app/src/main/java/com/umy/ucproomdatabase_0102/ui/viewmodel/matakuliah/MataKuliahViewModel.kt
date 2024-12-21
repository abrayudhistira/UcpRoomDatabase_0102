package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

class MataKuliahViewModel

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