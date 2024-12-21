package com.umy.ucproomdatabase_0102;

import android.app.Application
import com.umy.ucproomdatabase_0102.dependeciesinjection.ContainerApp
import com.umy.ucproomdatabase_0102.dependeciesinjection.InterfaceContainerApp

class ProdiApp : Application() {
    lateinit var containerApp: InterfaceContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}
