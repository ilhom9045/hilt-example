package com.example.daggerhiltinmultymoduleproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltinmultymoduleproject.R
import com.example.core.data.model.ResponseDTOModel
import javax.inject.Inject

class Adapter (private val items: List<ResponseDTOModel.Articles>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val textView:TextView = v.findViewById(R.id.textView)

        fun onbind(item: ResponseDTOModel.Articles) {
            textView.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onbind(items[position])
    }

    override fun getItemCount() = items.size
}