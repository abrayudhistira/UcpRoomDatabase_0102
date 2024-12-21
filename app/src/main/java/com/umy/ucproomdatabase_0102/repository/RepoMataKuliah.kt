package com.umy.ucproomdatabase_0102.repository

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepoMataKuliah {
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)

    fun getAllMataKuliah() : Flow<List<MataKuliah>>

    fun getDetail(kd_mk: String) : Flow<MataKuliah>

    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)

    suspend fun updateMataKuliah(mataKuliah: MataKuliah)
}