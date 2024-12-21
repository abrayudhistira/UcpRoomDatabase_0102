package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.umy.ucproomdatabase_0102.ProdiApp

object PenyediaDosenViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                ProdiApp().containerApp.repoDosen,
            )
        }
        initializer {
            HomeDosenViewModel(
                ProdiApp().containerApp.repoDosen,
            )
        }
    }
}

fun CreationExtras.ProdiApp(): ProdiApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ProdiApp)