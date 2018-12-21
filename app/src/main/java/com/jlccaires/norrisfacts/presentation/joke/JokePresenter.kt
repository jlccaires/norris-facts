package com.jlccaires.norrisfacts.presentation.joke

import com.jlccaires.norrisfacts.data.NorrisClient
import com.jlccaires.norrisfacts.presentation.addTo
import com.jlccaires.norrisfacts.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
class JokePresenter : BasePresenter<JokeContract.View>(), JokeContract.Presenter {

    @Bean
    protected lateinit var client: NorrisClient
    private lateinit var category: String

    override fun setCategory(category: String) {
        this.category = category
    }

    override fun jokeClicked() {
        getView().hideJoke()
    }

    override fun loadJoke() {
        getView().setLoading(true)
        client.api().getJoke(category)
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