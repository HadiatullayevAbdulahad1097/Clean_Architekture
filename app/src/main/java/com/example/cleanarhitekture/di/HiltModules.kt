package com.example.cleanarhitekture.di

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.cleanarhitekture.remote.MovieInterface
import com.example.cleanarhitekture.ui.useCase.GetNoteUseCase
import com.example.cleanarhitekture.ui.useCase.PostNoteUseCase
import com.example.cleanarhitekture.ui.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.acl.Owner

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MovieInterface::class.java)
    }

    @Provides
    fun providePostUseCase(movieInterface: MovieInterface): PostNoteUseCase {
        return PostNoteUseCase(movieInterface)
    }

    @Provides
    fun provideGetUseCase(movieInterface: MovieInterface): GetNoteUseCase {
        return GetNoteUseCase(movieInterface)
    }
}