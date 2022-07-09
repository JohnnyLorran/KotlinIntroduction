package com.example.annotations.di

import android.content.Context
import androidx.room.Room
import com.example.annotations.data.db.AppAnnotationDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun providesAppAnnotationDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppAnnotationDataBase::class.java, "app_database"
    ).build()

    @Provides
    fun providesAnnotationDAO(appAnnotationDataBase: AppAnnotationDataBase) =
        appAnnotationDataBase.annotationDAO
}