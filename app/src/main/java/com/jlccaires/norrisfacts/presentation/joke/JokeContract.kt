package com.jlccaires.norrisfacts.presentation.joke

import com.jlccaires.norrisfacts.presentation.base.BaseContract

interface JokeContract {
    interface View : BaseContract.View {
        fun setLoading(loading: Boolean)
        fun setJoke(joke: String?)
        fun setIcon(icon: String?)
        fun setUrl(url: String?)
        fun hideJoke()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadJoke()
        fun setCategory(category: String)
        fun jokeClicked()
    }
}