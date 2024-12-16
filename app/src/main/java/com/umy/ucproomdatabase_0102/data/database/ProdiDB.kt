package com.umy.ucproomdatabase_0102.data.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umy.ucproomdatabase_0102.data.dao.DosenDao
import com.umy.ucproomdatabase_0102.data.dao.MataKuliahDao
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

@Database(entities = [MataKuliah::class],[Dosen::class], version = 1, exportSchema = false)
abstract class ProdiDB : RoomDatabase() {

    abstract fun dosenDao():DosenDao
    abstract fun matakuliahDao():MataKuliahDao

    companion object {
        @Volatile
        private var Instance: ProdiDB? = null

        fun getDatabase(context: Context): ProdiDB {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ProdiDB::class.java,
                    "ProdiDB"
                )
                    .build().also { Instance = it }
            })
        }
    }

}