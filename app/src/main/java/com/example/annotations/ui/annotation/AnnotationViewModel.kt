package com.example.annotations.ui.annotation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.annotations.R

import com.example.annotations.repository.AnnotationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnotationViewModel @Inject constructor(private val repository: AnnotationRepository) :
    ViewModel() {


    //Utilizado para saber qual o estado da view
    private val _annotationStateEventData = MutableLiveData<AnnotationState>()
    val annotationStateEventData: LiveData<AnnotationState>
        get() = _annotationStateEventData

    //Utilizado para mensagens
    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData


    fun addOrUpdateSubscriber(
        name: String,
        surname: String,
        note1: String,
        note2: String,
        note3: String,
        id: Long = 0
    ) = viewModelScope.launch {
        if (id > 0) {
            updateAnnotation(id, name, surname, note1, note2, note3)
        } else {
            insertAnnotation(name, surname, note1, note2, note3)
        }
    }

    private fun updateAnnotation(
        id: Long,
        name: String,
        surname: String,
        note1: String,
        note2: String,
        note3: String
    ) = viewModelScope.launch {
        try {
            val id = repository.updateAnnotation(id, name, surname, note1, note2, note3)
            _annotationStateEventData.value = AnnotationState.Update
            _messageEventData.value = R.string.s_msg_update_sucess
        } catch (ex: Exception) {
            _messageEventData.value = R.string.s_msg_update_error
            Log.e(TAG, ex.toString())
        }
    }

    private fun insertAnnotation(
        name: String,
        surname: String,
        note1: String,
        note2: String,
        note3: String
    ) = viewModelScope.launch {
        try {
            val id = repository.insertAnnotation(name, surname, note1, note2, note3)
            if (id > 0) {
                _annotationStateEventData.value = AnnotationState.Inserted
                _messageEventData.value = R.string.s_msg_create_sucess
            } else {
                _messageEventData.value = R.string.s_msg_create_sucess
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    fun removeAnnotation(id: Long) = viewModelScope.launch {
        try {
            if (id > 0) {
                repository.deleteAnnotation(id)
                _annotationStateEventData.value = AnnotationState.Delete
                _messageEventData.value = R.string.s_msg_delete_sucess
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.s_msg_delete_error
            Log.e(TAG, ex.toString())
        }

    }


    //Vamos escutar os eventos da view
    sealed class AnnotationState {
        object Inserted : AnnotationState()
        object Update : AnnotationState()
        object Delete : AnnotationState()
    }

    companion object {
        private val TAG = AnnotationViewModel::class.java.simpleName
    }


}