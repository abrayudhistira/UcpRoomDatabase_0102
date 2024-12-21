package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import com.umy.ucproomdatabase_0102.data.entity.Dosen

data class HomeUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)