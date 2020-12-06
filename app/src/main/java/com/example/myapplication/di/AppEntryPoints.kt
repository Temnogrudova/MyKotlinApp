package com.example.myapplication.di

import com.example.myapplication.App
import com.example.myapplication.repos.LocalDb
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface AppEntryPoints {


    fun getYnetDb(): LocalDb
}


object AppEntryPointsImpl : AppEntryPoints {

    private val concreteAppEntryPoints: AppEntryPoints =
        EntryPointAccessors.fromApplication(App.applicationContext, AppEntryPoints::class.java)

    override fun getYnetDb() =  concreteAppEntryPoints.getYnetDb()
}

