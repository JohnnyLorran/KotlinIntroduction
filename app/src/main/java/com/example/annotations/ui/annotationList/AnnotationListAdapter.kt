package com.example.annotations.ui.annotationList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.annotations.R
import com.example.annotations.data.db.entity.AnnotationEntity
import kotlinx.android.synthetic.main.annotation_item.view.*

class AnnotationListAdapter(private val annotation: List<AnnotationEntity>,
    private val onAnnotationClickListerner: (annotation: AnnotationEntity) -> Unit) : RecyclerView.Adapter<AnnotationListAdapter.AnnotationListViewHolder>(){

    //inflando o layout annotation_item na view e retornando a view inflada como objeto de view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnotationListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.annotation_item, parent, false)
        return AnnotationListViewHolder(view, onAnnotationClickListerner)
    }

    override fun onBindViewHolder(holder: AnnotationListViewHolder, position: Int) {
        holder.bindView(annotation[position])
    }

    override fun getItemCount(): Int = annotation.size

    //Pegamos os text's do nosso card e definimimos que serão preenchidos com os dados do banco
    class AnnotationListViewHolder(ItemView: View,
    private val onAnnotationClickListerner: (annotation: AnnotationEntity) -> Unit) : RecyclerView.ViewHolder(ItemView){

        private val textViewAnnotationName: TextView = itemView.list_A_textName
        private val textViewAnnotationSurname: TextView = itemView.list_A_textSurname
        private val textViewAnnotationNote1: TextView = itemView.list_A_textNote1
        private val textViewAnnotationNote2: TextView = itemView.list_A_textNote2
        private val textViewAnnotationNote3: TextView = itemView.list_A_textNote3

        fun bindView(annotation: AnnotationEntity){
            textViewAnnotationName.text = annotation.name
            textViewAnnotationSurname.text = annotation.surname
            textViewAnnotationNote1.text = annotation.note1
            textViewAnnotationNote2.text = annotation.note2
            textViewAnnotationNote3.text = annotation.note3

            itemView.setOnClickListener{
                onAnnotationClickListerner?.invoke(annotation)
            }
        }

    }

}