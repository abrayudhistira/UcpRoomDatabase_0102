package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.umy.ucproomdatabase_0102.ProdiApp
import com.umy.ucproomdatabase_0102.data.database.ProdiDB
import com.umy.ucproomdatabase_0102.ui.viewmodel.dosen.ProdiApp

object PenyediaMataKuliahViewModel {

    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                ProdiApp().containerApp.repoMataKuliah
            )
        }
        initializer {
            HomeMataKuliahViewModel(
                ProdiApp().containerApp.repoMataKuliah
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                ProdiApp().containerApp.repoMataKuliah
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                ProdiApp().containerApp.repoMataKuliah
            )
        }
    }
}

fun CreationExtras.ProdiApp(): ProdiApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ProdiApp)