package com.jlccaires.norrisfacts.presentation.base

interface BaseContract {

    interface View {
        fun showError()
    }

    interface Presenter<VT : View?> {

        fun attachView(view: VT)

        fun dispose()
    }
}