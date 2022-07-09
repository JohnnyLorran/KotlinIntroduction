package com.example.annotations.ui.annotationList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.annotations.R
import com.example.annotations.extension.navigateWithAnimations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_annotation_list.*


@AndroidEntryPoint
class AnnotationListFragment : Fragment(R.layout.fragment_annotation_list) {

    private val viewModel: AnnotationListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListerners()
    }


    private fun observeViewModelEvents() {
        viewModel.allAnnotationEvent.observe(viewLifecycleOwner) { allAnnotation ->
            val annotationListAdapter = AnnotationListAdapter(allAnnotation) { annotation ->
                val directions =
                    AnnotationListFragmentDirections.actionAnnotationListFragmentToAnnotationFragment(
                        annotation
                    )

                findNavController().navigateWithAnimations(directions)
            }
            with(list_A_rc) {
                setHasFixedSize(false)
                adapter = annotationListAdapter

            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAnnotations()
    }

    private fun configureViewListerners() {
        list_A_FAB_new.setOnClickListener {

            findNavController().navigateWithAnimations(AnnotationListFragmentDirections.actionAnnotationListFragmentToAnnotationFragment(null))
        }
    }
}



