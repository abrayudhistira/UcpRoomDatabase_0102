package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

class DetailMataKuliahViewModel {
}

fun MataKuliah.toDetailMataKuliahEvent(): MataKuliahEvent {
    return MataKuliahEvent(
        kd_mk = kd_mk,
        nama_mk = nama_mk,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosen_pengampu = dosen_pengampu
    )
}