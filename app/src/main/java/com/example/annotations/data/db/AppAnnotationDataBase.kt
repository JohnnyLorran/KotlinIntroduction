package com.example.annotations.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.annotations.data.db.dao.AnnotationDAO
import com.example.annotations.data.db.entity.AnnotationEntity

@Database(entities = [AnnotationEntity::class], version = 1)
abstract class AppAnnotationDataBase : RoomDatabase() {

    abstract val annotationDAO: AnnotationDAO

}