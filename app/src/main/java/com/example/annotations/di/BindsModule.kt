package com.example.annotations.di

import com.example.annotations.repository.AnnotationRepository
import com.example.annotations.repository.DatabaseDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindsDataBaseDataSource(databaseDataSource: DatabaseDataSource): AnnotationRepository

}