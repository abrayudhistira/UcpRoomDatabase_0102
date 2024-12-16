package com.umy.ucproomdatabase_0102.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umy.ucproomdatabase_0102.data.entity.Dosen
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

@Database(entities = [MataKuliah::class],[Dosen::class], version = 1, exportSchema = false)
class ProdiDB : RoomDatabase() {

}