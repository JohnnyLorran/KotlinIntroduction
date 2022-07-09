package com.example.annotations.repository

import com.example.annotations.data.db.entity.AnnotationEntity

//conecta o aplicativo com o room
interface AnnotationRepository {

    suspend fun insertAnnotation(name: String, surname: String, note1: String, note2: String, note3: String): Long

    suspend fun updateAnnotation(id: Long, name: String, surname: String, note1: String, note2: String, note3: String)

    suspend fun getAllAnnotation(): List<AnnotationEntity>

    suspend fun deleteAnnotation(id: Long)

}