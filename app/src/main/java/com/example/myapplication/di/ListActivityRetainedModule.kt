package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.network.WebService
import com.example.myapplication.repos.ListRepository
import com.example.myapplication.repos.ListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient

@Module
@InstallIn(ActivityRetainedComponent::class)
interface  ListActivityRetainedModuleBinds {

    @Binds
    fun bindListRepository(
        listRepositoryImpl: ListRepositoryImpl
    ) : ListRepository

}

@Module
@InstallIn(ActivityRetainedComponent::class)
object  ListActivityRetainedModule {
    @Provides
    @ActivityRetainedScoped
    fun provideWebService(@GlobalOkHttpClient okHttpClient:OkHttpClient) : WebService = createWebService(okHttpClient = okHttpClient, url = BuildConfig.SERVER_URL)
}
