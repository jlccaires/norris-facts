package com.jlccaires.norrisfacts.presentation.category

import com.jlccaires.norrisfacts.presentation.base.BaseContract

interface CategoriesContract {
    interface View : BaseContract.View {
        fun setLoading(loading: Boolean)
        fun setCategories(categories: List<String>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadCategories()
    }
}