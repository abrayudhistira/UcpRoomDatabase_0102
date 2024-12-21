package com.umy.ucproomdatabase_0102.ui.viewmodel.matakuliah

import com.umy.ucproomdatabase_0102.data.entity.MataKuliah

class UpdateMataKuliahViewModel {
}



fun MataKuliah.toUiStateMataKuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMataKuliahEvent(),
)