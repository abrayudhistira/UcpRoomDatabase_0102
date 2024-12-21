package com.umy.ucproomdatabase_0102.dependeciesinjection

import android.content.Context
import com.umy.ucproomdatabase_0102.data.database.ProdiDB
import com.umy.ucproomdatabase_0102.repository.LocalRepoDosen
import com.umy.ucproomdatabase_0102.repository.LocalRepoMataKuliah
import com.umy.ucproomdatabase_0102.repository.RepoDosen
import com.umy.ucproomdatabase_0102.repository.RepoMataKuliah

interface InterfaceContainerApp {
    val repoDosen: RepoDosen
    val repoMataKuliah: RepoMataKuliah
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repoDosen: RepoDosen by lazy {
        LocalRepoDosen(ProdiDB.getDatabase(context).dosenDao())
    }

    override val repoMataKuliah: RepoMataKuliah by lazy {
        LocalRepoMataKuliah(ProdiDB.getDatabase(context).matakuliahDao())
    }
}