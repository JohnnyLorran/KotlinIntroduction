package com.example.annotations.data.db.dao

import androidx.room.*
import com.example.annotations.data.db.entity.AnnotationEntity

//cria consultas assincronas
@Dao
interface AnnotationDAO {
    @Insert
    suspend fun insertAnnotation(annotation: AnnotationEntity): Long

    @Update
    suspend fun updateAnnotation(annotation: AnnotationEntity)

    @Query("SELECT * FROM annotations")
    suspend fun getAllAnnotations(): List<AnnotationEntity>

    @Query("DELETE FROM annotations WHERE id = :id")
    suspend fun deleteAnnotation(id: Long)
}