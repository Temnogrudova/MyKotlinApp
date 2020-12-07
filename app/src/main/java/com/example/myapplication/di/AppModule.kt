package com.example.myapplication.di

import android.content.Context

import androidx.room.Room
import com.example.myapplication.repos.LocalDb
import com.example.myapplication.repos.MIGRATION_FORM_1_TO_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private const val GENERAL_LOCAL_DB = "local_db"

    @Provides
    @Singleton
    fun provideLocalYnetDb(@ApplicationContext appContext: Context): LocalDb =
        Room.databaseBuilder(appContext, LocalDb::class.java, GENERAL_LOCAL_DB)
            .addMigrations(MIGRATION_FORM_1_TO_2)
            .build()
}


