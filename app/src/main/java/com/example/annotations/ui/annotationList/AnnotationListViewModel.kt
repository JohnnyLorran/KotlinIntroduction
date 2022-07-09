package com.example.annotations.ui.annotationList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.annotations.data.db.entity.AnnotationEntity
import com.example.annotations.repository.AnnotationRepository
import com.example.annotations.ui.annotation.AnnotationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnotationListViewModel @Inject constructor(private val repository: AnnotationRepository) :
    ViewModel() {

    private val _allAnnotationEvent = MutableLiveData<List<AnnotationEntity>>()
    val allAnnotationEvent: LiveData<List<AnnotationEntity>>
        get() = _allAnnotationEvent

    fun getAnnotations() =
        viewModelScope.launch { _allAnnotationEvent.postValue(repository.getAllAnnotation()) }

    companion object {
        private val TAG = AnnotationViewModel::class.java.simpleName
    }

}