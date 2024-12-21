package com.umy.ucproomdatabase_0102.repository

import com.umy.ucproomdatabase_0102.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepoDosen {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>
}