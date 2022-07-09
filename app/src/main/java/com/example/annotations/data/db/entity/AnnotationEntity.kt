package com.example.annotations.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
// transforma a dataclass em uma entity do room
@Entity(tableName = "annotations")
data class AnnotationEntity (
    //transforma o ID em chave primária e diz que sempre que tiver uma inserção ele vai se preenche automaticamente com o último id+1
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val surname: String,
    val note1: String,
    val note2: String,
    val note3: String
) : Parcelable