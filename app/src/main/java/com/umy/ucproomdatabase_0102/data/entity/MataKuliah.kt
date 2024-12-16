package com.umy.ucproomdatabase_0102.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class MataKuliah(
    @PrimaryKey
    val kd_mk: String,
    val nama_mk: String,
    val sks: String,
    val semester: String,
    val jenis: String,
    val dosen_pengampu: String
)

