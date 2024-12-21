package com.umy.ucproomdatabase_0102.repository

import com.umy.ucproomdatabase_0102.data.dao.MataKuliahDao
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepoMataKuliah (
    private val mataKuliahDao: MataKuliahDao
) : RepoMataKuliah {
    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMK()
    }

    override fun getDetail(kd_mk: String): Flow<MataKuliah> {
        return mataKuliahDao.getMK(kd_mk)
    }

    override suspend fun deleteMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMK(mataKuliah)
    }

    override suspend fun updateMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMK(mataKuliah)
    }

    override suspend fun insertMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMK(mataKuliah)
    }
}