package com.jlccaires.norrisfacts.presentation.category

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jlccaires.norrisfacts.R
import com.jlccaires.norrisfacts.presentation.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.category_item_layout.view.*
import java.util.concurrent.TimeUnit

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    val data = ArrayList<String>()
    private lateinit var listener: (String) -> Unit

    override fun getItemCount() = data.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position].capitalize())
        holder.view.clicks()
            .debounce(250, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { listener.invoke(data[holder.adapterPosition]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(parent.inflate(R.layout.category_item_layout))

    fun clickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    class CategoryViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(category: String) {
            view.tvCategory.text = category
        }
    }
}