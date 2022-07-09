package com.example.annotations.ui.annotation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.annotations.R
import com.example.annotations.data.db.AppAnnotationDataBase
import com.example.annotations.data.db.dao.AnnotationDAO
import com.example.annotations.extension.hideKeyBoard
import com.example.annotations.extension.navigateWithAnimations
import com.example.annotations.repository.AnnotationRepository
import com.example.annotations.repository.DatabaseDataSource
import com.example.annotations.ui.annotationList.AnnotationListFragmentDirections
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.annotation_item.*
import kotlinx.android.synthetic.main.fragment_annotation.*


//Passar o layout direto no fragment, já vai inflar ele
@AndroidEntryPoint
class AnnotationFragment : Fragment(R.layout.fragment_annotation) {

    private val viewModel: AnnotationViewModel by viewModels()

    private val args: AnnotationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.annotationEntity?.let { annotationEntity ->
            btn_A_create.text = getString(R.string.s_btn_A_update)
            input_A_name.setText(annotationEntity.name)
            input_A_surname.setText(annotationEntity.surname)
            input_A_note1.setText(annotationEntity.note1)
            input_A_note2.setText(annotationEntity.note2)
            input_A_note3.setText(annotationEntity.note3)

            btn_A_delete.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()

    }


    private fun observeEvents() {
        viewModel.annotationStateEventData.observe(viewLifecycleOwner) { annotationState ->
            when (annotationState) {
                is AnnotationViewModel.AnnotationState.Inserted,
                is AnnotationViewModel.AnnotationState.Update,
                is AnnotationViewModel.AnnotationState.Delete -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }


    private fun clearFields() {
        input_A_name.text?.clear()
        input_A_surname.text?.clear()
        input_A_note1.text?.clear()
        input_A_note2.text?.clear()
        input_A_note3.text?.clear()

    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyBoard()
        }
    }

    private fun setListeners() {
        btn_A_create.setOnClickListener {
            val name = input_A_name.text.toString()
            val surname = input_A_surname.text.toString()
            val note1 = input_A_note1.text.toString()
            val note2 = input_A_note2.text.toString()
            val note3 = input_A_note3.text.toString()

            viewModel.addOrUpdateSubscriber(
                name,
                surname,
                note1,
                note2,
                note3,
                args.annotationEntity?.id ?: 0
            )
        }

        btn_A_delete.setOnClickListener {
            viewModel.removeAnnotation(args.annotationEntity?.id ?: 0)
        }

    }


}