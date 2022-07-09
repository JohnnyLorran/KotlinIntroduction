package com.example.annotations.repository

import com.example.annotations.data.db.dao.AnnotationDAO
import com.example.annotations.data.db.entity.AnnotationEntity
import javax.inject.Inject


class DatabaseDataSource @Inject constructor(private val annotationDAO: AnnotationDAO) :
    AnnotationRepository {

    override suspend fun insertAnnotation(
        name: String,
        surname: String,
        note1: String,
        note2: String,
        note3: String
    ): Long {
        val annotation = AnnotationEntity(
            name = name,
            surname = surname,
            note1 = note1,
            note2 = note2,
            note3 = note3
        )

        return annotationDAO.insertAnnotation(annotation)
    }

    override suspend fun updateAnnotation(
        id: Long,
        name: String,
        surname: String,
        note1: String,
        note2: String,
        note3: String
    ) {
        val annotation = AnnotationEntity(
            id = id,
            name = name,
            surname = surname,
            note1 = note1,
            note2 = note2,
            note3 = note3
        )

        annotationDAO.updateAnnotation(annotation)
    }

    override suspend fun getAllAnnotation(): List<AnnotationEntity> {
        return annotationDAO.getAllAnnotations()
    }

    override suspend fun deleteAnnotation(id: Long) {
        annotationDAO.deleteAnnotation(id)
    }

}