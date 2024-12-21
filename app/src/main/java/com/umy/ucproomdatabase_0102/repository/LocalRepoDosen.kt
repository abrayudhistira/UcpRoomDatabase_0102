package com.umy.ucproomdatabase_0102.repository

import com.umy.ucproomdatabase_0102.data.dao.DosenDao
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepoDosen (
    private val dosenDao: DosenDao
) : RepoDosen {
    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getDosen()
    }

    override suspend fun insertDosen(dosen: Dosen) {
        return dosenDao.insertDosen(dosen)
    }
}