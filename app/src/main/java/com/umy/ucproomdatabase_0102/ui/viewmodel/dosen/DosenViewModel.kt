package com.umy.ucproomdatabase_0102.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import com.umy.ucproomdatabase_0102.repository.RepoDosen

class DosenViewModel (private val repoDosen: RepoDosen) : ViewModel() {

}

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
)