package com.umy.ucproomdatabase_0102.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Insert
    suspend fun insertMK(mataKuliah: MataKuliah)

    @Query("SELECT * FROM matakuliah ORDER BY ASC")
    fun getAllMK(): Flow<List<MataKuliah>>

    @Query("SELECT * FROM matakuliah WHERE kd_mk = :kd_mk")
    fun getMK(kd_mk: String) : Flow<MataKuliah>

    @Delete
    suspend fun deleteMK(mataKuliah: MataKuliah)

    @Update
    suspend fun  updateMK(mataKuliah: MataKuliah)
}