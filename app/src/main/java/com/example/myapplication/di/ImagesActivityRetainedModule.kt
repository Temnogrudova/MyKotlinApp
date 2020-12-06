package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.network.ImagesWebService
import com.example.myapplication.repos.ImagesRepository
import com.goldtouch.ynet.repos.my_news.ImagesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient

@Module
@InstallIn(ActivityRetainedComponent::class)
interface  ImagesActivityRetainedModuleBinds {

    @Binds
    fun bindImagesRepository(
        imagesRepositoryImpl: ImagesRepositoryImpl
    ) : ImagesRepository

}

@Module
@InstallIn(ActivityRetainedComponent::class)
object  ImagesActivityRetainedModule {
    @Provides
    @ActivityRetainedScoped
    fun provideImagesWebService( @GlobalOkHttpClient okHttpClient:OkHttpClient) : ImagesWebService = createWebService(okHttpClient = okHttpClient, url = BuildConfig.SERVER_URL)
}
