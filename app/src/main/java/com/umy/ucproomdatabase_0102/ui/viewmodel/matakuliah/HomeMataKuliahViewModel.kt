package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import com.umy.ucproomdatabase_0102.data.entity.MataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah

class HomeMataKuliahViewModel (
    private val repoMataKuliah: RepoMataKuliah
) : ViewModel() {
}

data class HomeMataKuliahUiState(
    val listMataKuliah: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)