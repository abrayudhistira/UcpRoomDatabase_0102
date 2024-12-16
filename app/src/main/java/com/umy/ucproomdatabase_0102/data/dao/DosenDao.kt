package com.umy.ucproomdatabase_0102.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query("SELECT * From dosen ORDER BY nama ASC")
    fun getDosen(): Flow<List<Dosen>>
}