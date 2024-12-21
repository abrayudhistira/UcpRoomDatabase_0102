package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

class MataKuliahViewModel

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