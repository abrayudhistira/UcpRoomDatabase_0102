package com.umy.ucproomdatabase_0102.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHalamanUtama : AlamatNavigasi {
    override val route = "dashboard"
}

object DestinasiDosen : AlamatNavigasi {
    override val route = "dosen"
}


object DestinasiMataKuliah : AlamatNavigasi {
    override val route = "matakuliah"
}

object DestinasiMataKuliahDetail : AlamatNavigasi {
    override val route = "matakuliahdetail"
    const val KD_MK = "kd_mk"
    val routesWithArg = "$route/{$KD_MK}"
}

object DestinasiMataKuliahUpdate : AlamatNavigasi {
    override val route = "matakuliahupdate"
    const val KD_MK = "kd_mk"
    val routesWithArg = "$route/{$KD_MK}"
}