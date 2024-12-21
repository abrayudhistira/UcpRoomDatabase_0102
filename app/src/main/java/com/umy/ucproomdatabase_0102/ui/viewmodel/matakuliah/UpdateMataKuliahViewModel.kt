package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

class UpdateMataKuliahViewModel {
}


fun resetSnackBarMessage() {
    updateUIState = updateUIState.copy(snackBarMessage = null)
}

fun MataKuliah.toUiStateMataKuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMataKuliahEvent(),
)