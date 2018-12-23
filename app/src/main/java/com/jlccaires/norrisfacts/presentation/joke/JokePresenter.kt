package com.jlccaires.norrisfacts.presentation.joke

import com.jlccaires.norrisfacts.data.NorrisService
import com.jlccaires.norrisfacts.presentation.addTo
import com.jlccaires.norrisfacts.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class JokePresenter : BasePresenter<JokeContract.View>, JokeContract.Presenter {

    private val service: NorrisService
    private lateinit var category: String

    @Inject
    constructor(service: NorrisService) {
        this.service = service
    }

    override fun setCategory(category: String) {
        this.category = category
    }

    override fun jokeClicked() {
        getView().hideJoke()
    }

    override fun loadJoke() {
        getView().setLoading(true)
        service.getJoke(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { joke ->
                    getView().setJoke("“${joke.value}”")
                    getView().setIcon(joke.iconUrl)
                    getView().setUrl(joke.url)
                    getView().setLoading(false)
                },
                {
                    getView().showError()
                }
            ).addTo(disposables)

    }

}