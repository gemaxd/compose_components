package com.example.components.di

import com.example.components.feature.dynamic_form.data.repository.DynamicFormRepositoryImpl
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDynamicFormRepository(): DynamicFormRepository {
        return DynamicFormRepositoryImpl()
    }

}